package utilities.consolemenu.input;

import utilities.consolemenu.exception.InputException;

import java.util.Set;

/**
 * Interface for validating and processing user input.
 */
public interface InputValidator {
    String getValidatedInput(Set<String> validOptions) throws InputException;
}
