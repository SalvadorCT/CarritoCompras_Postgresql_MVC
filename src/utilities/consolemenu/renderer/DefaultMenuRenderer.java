package utilities.consolemenu.renderer;

import utilities.consolemenu.model.Menu;
import utilities.consolemenu.model.MenuItem;

import java.util.ResourceBundle;

/**
 * Default implementation of MenuRenderer that prints to the console.
 */
public class DefaultMenuRenderer implements MenuRenderer {
    private ResourceBundle messages;

    public DefaultMenuRenderer() {
        this.messages = ResourceBundle.getBundle("utilities.consolemenu.util.messages");

    }

    @Override
    public void render(Menu menu) {
        System.out.println("\n=== " + messages.getString("menu.title") + " ===");
        for (MenuItem item : menu.getItems().values()) {
            System.out.println(item.getKey() + ". " + item.getDescription());
        }
    }
}
