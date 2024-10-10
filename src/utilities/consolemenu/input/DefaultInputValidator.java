package utilities.consolemenu.input;

import utilities.consolemenu.exception.InputException;

import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Default implementation of InputValidator using Scanner.
 */
public class DefaultInputValidator implements InputValidator {
    private static final Logger logger = Logger.getLogger(DefaultInputValidator.class.getName());
    private Scanner scanner;

    public DefaultInputValidator() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String getValidatedInput(Set<String> validOptions) throws InputException {
        String input;
        while (true) {
            System.out.print("Select an option: ");
            input = scanner.nextLine().trim();
            if (validOptions.contains(input)) {
                //logger.log(Level.FINE, "User selected option: {0}", input);
                // logger.log(Level.INFO, "User selected option: {0}", input);
                return input;
            } else {
                //logger.log(Level.WARNING, "Invalid option entered: {0}", input);
                System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
