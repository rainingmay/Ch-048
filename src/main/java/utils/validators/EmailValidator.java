package utils.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gregtar on 12.04.17.
 */
public class EmailValidator {
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private final Pattern pattern;
    private Matcher matcher;

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
