package utilities.consoleinput.validators;

import java.util.function.Predicate;

/**
 * Represents a validation rule with an associated error message.
 * @param <T> The type of the input to validate.
 */
public class ValidationRule<T> {
    private Predicate<T> condition;
    private String errorMessage;

    public ValidationRule(Predicate<T> condition, String errorMessage) {
        this.condition = condition;
        this.errorMessage = errorMessage;
    }

    public Predicate<T> getCondition() {
        return condition;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setCondition(Predicate<T> condition) {
        this.condition = condition;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
