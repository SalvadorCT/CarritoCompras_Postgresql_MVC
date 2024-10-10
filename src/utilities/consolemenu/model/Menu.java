package utilities.consolemenu.model;

import utilities.consolemenu.command.Command;
import utilities.consolemenu.exception.MenuException;
import utilities.consolemenu.input.InputValidator;
import utilities.consolemenu.renderer.MenuRenderer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a menu in the console application.
 * Supports adding commands and nested submenus.
 */
public class Menu implements Command {
    private String title;
    private Map<String, MenuItem> items;
    private InputValidator inputValidator;
    private MenuRenderer renderer;
    private boolean exitOnCompletion;

    public Menu(String title) {
        this.title = title;
        this.items = new LinkedHashMap<>();
        this.exitOnCompletion = false;
    }

    public Menu(String title, InputValidator inputValidator, MenuRenderer renderer){
        this.title = title;
        this.items = new LinkedHashMap<>();
        this.exitOnCompletion = false;
        this.inputValidator = inputValidator;
        this.renderer = renderer;
    }

    public void setInputValidator(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public void setRenderer(MenuRenderer renderer) {
        this.renderer = renderer;
    }

    /**
     * Adds a command item to the menu.
     */
    public void addCommandItem(String key, String description, Command command) {
        items.put(key, new MenuItem(key, description, command));
    }

    /**
     * Adds a submenu item to the menu.
     */
    public void addSubmenuItem(String key, String description, Menu submenu) {
        items.put(key, new MenuItem(key, description, submenu));
    }

    /**
     * Sets whether the menu should exit after completion.
     */
    public void setExitOnCompletion(boolean exitOnCompletion) {
        this.exitOnCompletion = exitOnCompletion;
    }

    /**
     * Executes the menu (used when the menu is a submenu).
     */
    @Override
    public void execute() throws Exception {
        display();
    }

    /**
     * Displays the menu and handles user interaction.
     */
    public void display() throws Exception {
        if (renderer == null || inputValidator == null) {
            throw new IllegalStateException("Renderer and InputValidator must be set before displaying the menu.");
        }

        while (true) {
            renderer.render(this);

            String choice = inputValidator.getValidatedInput(items.keySet());

            MenuItem selectedItem = items.get(choice);
            if (selectedItem != null) {
                if (selectedItem.isSubmenu()) {
                    selectedItem.getSubmenu().setInputValidator(inputValidator);
                    selectedItem.getSubmenu().setRenderer(renderer);
                    selectedItem.getSubmenu().display();
                } else {
                    selectedItem.getCommand().execute();
                }
            } else {
                System.out.println("Invalid option.");
            }

            if (exitOnCompletion) {
                break;
            }
        }
    }

    public void exit() {
        this.exitOnCompletion = true;
    }


    public String getTitle() {
        return title;
    }

    public Map<String, MenuItem> getItems() {
        return items;
    }
}
