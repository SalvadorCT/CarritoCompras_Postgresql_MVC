package utilities.consolemenu.command;

/**
 * Command to exit the application or a menu.
 */
public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Exiting the application...");
        System.exit(0);
    }
}
