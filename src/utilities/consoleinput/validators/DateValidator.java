package utilities.consoleinput.validators;

import utilities.consoleinput.exceptions.ValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Predicate;

/**
 * Validator for date inputs.
 */
public class DateValidator implements InputValidator<LocalDate> {
    private DateTimeFormatter formatter;
    private Predicate<LocalDate> condition;
    private String errorMessage;

    public DateValidator() {
        this.formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    }

    public DateValidator withFormat(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
        return this;
    }

    public DateValidator condition(Predicate<LocalDate> condition, String errorMessage) {
        this.condition = condition;
        this.errorMessage = errorMessage;
        return this;
    }

    @Override
    public LocalDate validate(String input) throws ValidationException {
        try {
            LocalDate date = LocalDate.parse(input, formatter);
            if (condition != null && !condition.test(date)) {
                throw new ValidationException(errorMessage != null ? errorMessage : "Fecha inválida.");
            }
            return date;
        } catch (DateTimeParseException e) {
            throw new ValidationException("Debe ingresar una fecha válida en el formato " + formatter.toString());
        }
    }
}
