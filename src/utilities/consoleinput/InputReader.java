package utilities.consoleinput;

import utilities.consoleinput.exceptions.ValidationException;
import utilities.consoleinput.validators.InputValidator;

import java.util.Scanner;

/**
 * Utility class for reading and validating console input.
 */
public class InputReader {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads input from the console and validates it using the provided validator.
     * @param prompt The prompt message to display.
     * @param validator The input validator to use.
     * @param <T> The type of the validated input.
     * @return The validated input value.
     */
    public static <T> T read(String prompt, InputValidator<T> validator) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return validator.validate(input);
            } catch (ValidationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

}
