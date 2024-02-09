package dev.mmartins.jwtverifyapi.application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator implements ClaimValidator {
    private final String name;

    public NameValidator(final String name) {
        this.name = name;
    }

    @Override
    public boolean isValid() {
        Pattern pattern = Pattern.compile(".*\\d.*");
        Matcher matcher = pattern.matcher(this.name);
        return !matcher.matches() && !this.name.isEmpty() && this.name.length() <= 256;
    }
}
