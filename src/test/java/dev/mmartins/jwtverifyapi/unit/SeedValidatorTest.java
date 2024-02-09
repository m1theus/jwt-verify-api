package dev.mmartins.jwtverifyapi.unit;

import dev.mmartins.jwtverifyapi.application.SeedValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeedValidatorTest {
    @Test
    void shouldReturnFalseIfIsNotAPrimeNumber() {
        SeedValidator validator = new SeedValidator(1);
        assertFalse(validator.isValid());
    }

    @Test
    void shouldReturnTrueIfIsAPrimeNumber() {
        SeedValidator validator = new SeedValidator(997);
        assertTrue(validator.isValid());
    }
}
