package utilities.consolemenu.model;

import utilities.consolemenu.command.Command;

/**
 * Represents an item in the menu, which can be either a command or a submenu.
 */
public class MenuItem {
    private String key;
    private String description;
    private Command command;
    private Menu submenu;

    // Constructor for command items
    public MenuItem(String key, String description, Command command) {
        this.key = key;
        this.description = description;
        this.command = command;
    }

    // Constructor for submenu items
    public MenuItem(String key, String description, Menu submenu) {
        this.key = key;
        this.description = description;
        this.submenu = submenu;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSubmenu() {
        return submenu != null;
    }

    public Command getCommand() {
        return command;
    }

    public Menu getSubmenu() {
        return submenu;
    }
}
