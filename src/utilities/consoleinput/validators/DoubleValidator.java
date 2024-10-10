package utilities.consoleinput.validators;

import utilities.consoleinput.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Validator for double inputs.
 */
public class DoubleValidator implements InputValidator<Double> {
    private Double minValue;
    private Double maxValue;
    private List<Predicate<Double>> conditions;
    private String errorMessage;

    public DoubleValidator() {
        this.conditions = new ArrayList<>();
    }

    public DoubleValidator min(double minValue) {
        this.minValue = minValue;
        return this;
    }

    public DoubleValidator max(double maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public DoubleValidator condition(Predicate<Double> condition, String errorMessage) {
        this.conditions.add(condition);
        this.errorMessage = errorMessage;
        return this;
    }

    @Override
    public Double validate(String input) throws ValidationException {
        try {
            double value = Double.parseDouble(input);

            if (minValue != null && value < minValue) {
                throw new ValidationException("El número debe ser mayor o igual a " + minValue);
            }

            if (maxValue != null && value > maxValue) {
                throw new ValidationException("El número debe ser menor o igual a " + maxValue);
            }

            for (Predicate<Double> condition : conditions) {
                if (!condition.test(value)) {
                    throw new ValidationException(errorMessage != null ? errorMessage : "Entrada inválida.");
                }
            }

            return value;
        } catch (NumberFormatException e) {
            throw new ValidationException("Debe ingresar un número válido.");
        }
    }
}
