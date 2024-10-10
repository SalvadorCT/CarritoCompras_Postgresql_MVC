package utilities.consoleinput;

import utilities.consoleinput.validators.*;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Factory class for obtaining input validators.
 */
public class ValidatorFactory {

    // Predefined Validators

    public static StringValidator emailValidator() {
        return new StringValidator()
                .matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")
                .errorMessage("Debe ingresar una dirección de correo electrónico válida.");
    }

    public static StringValidator phoneNumberValidator() {
        return new StringValidator()
                .matches("\\d{10}")
                .errorMessage("El número de teléfono debe tener 10 dígitos.");
    }

    public static StringValidator passwordValidator() {
        return new StringValidator()
                .minLength(8)
                .condition(s -> Pattern.compile("[A-Z]").matcher(s).find(), "La contraseña debe contener al menos una letra mayúscula.")
                .condition(s -> Pattern.compile("[a-z]").matcher(s).find(), "La contraseña debe contener al menos una letra minúscula.")
                .condition(s -> Pattern.compile("[0-9]").matcher(s).find(), "La contraseña debe contener al menos un número.")
                .condition(s -> Pattern.compile("[^A-Za-z0-9]").matcher(s).find(), "La contraseña debe contener al menos un carácter especial.")
                .errorMessage("La contraseña no cumple con los requisitos de seguridad.");
    }

    public static StringValidator idValidator() {
        return new StringValidator()
                .matches("[A-Z]{2}\\d{4}")
                .errorMessage("El ID debe tener el formato: dos letras mayúsculas seguidas de cuatro dígitos (por ejemplo, AB1234).");
    }

    public static DateValidator futureDateValidator() {
        return new DateValidator()
                .condition(date -> !date.isBefore(LocalDate.now()), "La fecha debe ser hoy o una fecha futura.");
    }

    // Custom Validators can be added as needed
}
