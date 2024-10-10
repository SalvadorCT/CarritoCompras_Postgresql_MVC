package utilities.consoleinput;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility class for managing localized messages.
 */
public class Messages {
    private static Locale locale = Locale.getDefault();
    private static ResourceBundle bundle = ResourceBundle.getBundle("utilities.consoleinput.resources.messages", locale);

    public static void setLocale(Locale newLocale) {
        locale = newLocale;
        bundle = ResourceBundle.getBundle("utilities.consoleinput.resources.messages", locale);
    }

    public static String get(String key, Object... params) {
        String pattern = bundle.getString(key);
        return MessageFormat.format(pattern, params);
    }
}
