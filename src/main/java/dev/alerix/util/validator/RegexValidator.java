package dev.alerix.util.validator;

public class RegexValidator implements Validator {
    private final String regex;

    public RegexValidator(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean isValid(String data) {
        return data.matches(regex);
    }
}
