package utilities.consoleinput.validators;

import utilities.consoleinput.exceptions.ValidationException;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Validator for string inputs.
 */
public class StringValidator implements InputValidator<String> {
    private Integer minLength;
    private Integer maxLength;
    private Pattern pattern;
    private final List<ValidationRule<String>> rules = new ArrayList<>();

    public StringValidator minLength(int minLength) {
        this.minLength = minLength;
        return this;
    }

    public StringValidator maxLength(int maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public StringValidator matches(String regex) {
        this.pattern = Pattern.compile(regex);
        return this;
    }

    public StringValidator condition(Predicate<String> condition, String errorMessage) {
        rules.add(new ValidationRule<>(condition, errorMessage));
        return this;
    }

    public StringValidator errorMessage(String errorMessage) {
        if (!rules.isEmpty()) {
            rules.get(rules.size() - 1).setErrorMessage(errorMessage);
        }
        return this;
    }

    public StringValidator phoneNumber() {
        return matches("^\\+?[0-9]{7,15}$") // Ajusta la expresión regular para el formato de teléfono que desees.
                .errorMessage("El número de teléfono no es válido.");
    }


    public StringValidator email() {
        return matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
                .errorMessage("El correo electrónico no es válido.");
    }

    public StringValidator password(){
        return matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
                .errorMessage("El password electrónico no es válido.");
    }



    @Override
    public String validate(String input) throws ValidationException {
        if (minLength != null && input.length() < minLength) {
            throw new ValidationException("La entrada debe tener al menos " + minLength + " caracteres.");
        }

        if (maxLength != null && input.length() > maxLength) {
            throw new ValidationException("La entrada debe tener como máximo " + maxLength + " caracteres.");
        }

        if (pattern != null && !pattern.matcher(input).matches()) {
            throw new ValidationException("La entrada no cumple con el formato requerido.");
        }

        for (ValidationRule<String> rule : rules) {
            if (!rule.getCondition().test(input)) {
                throw new ValidationException(rule.getErrorMessage());
            }
        }

        return input;
    }
}
