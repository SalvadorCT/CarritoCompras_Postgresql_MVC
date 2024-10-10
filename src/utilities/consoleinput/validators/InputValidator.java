package utilities.consoleinput.validators;

import utilities.consoleinput.exceptions.ValidationException;

/**
 * Interface for input validators.
 * @param <T> The type of the input to validate.
 */
public interface InputValidator<T> {
    /**
     * Validates the input and returns the parsed value.
     * @param input The input string to validate.
     * @return The validated and parsed value.
     * @throws ValidationException If the input is invalid.
     */
    T validate(String input) throws ValidationException;
}
