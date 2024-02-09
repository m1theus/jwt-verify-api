package dev.mmartins.jwtverifyapi.unit;

import dev.mmartins.jwtverifyapi.application.NameValidator;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NameValidatorTest {
    @Test
    void shouldReturnFalseIfNameLengthIsInvalid() {
        NameValidator validator = new NameValidator("");
        assertFalse(validator.isValid());
    }

    @Test
    void shouldReturnFalseIfNameLengthIsOversize() {
        final var name = generateRandomString(257);
        NameValidator validator = new NameValidator(name);
        assertFalse(validator.isValid());
    }

    @Test
    void shouldReturnFalseIfNameHasNumbers() {
        NameValidator validator = new NameValidator("M4r1a");
        assertFalse(validator.isValid());
    }

    @Test
    void shouldReturnTrueIfNameIsValid() {
        NameValidator validator = new NameValidator("José Da Silva");
        assertTrue(validator.isValid());
    }

    @Test
    void shouldReturnTrueIfNameIsHasMaxLength() {
        final var name = generateRandomString(256);
        NameValidator validator = new NameValidator(name);
        assertTrue(validator.isValid());
    }

    private static String generateRandomString(final int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        final StringBuilder randomString = new StringBuilder();
        final Random random = new Random();
        final var characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < length; i++) {
            final int randomIndex = random.nextInt(characters.length());
            final char randomChar = characters.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
}
