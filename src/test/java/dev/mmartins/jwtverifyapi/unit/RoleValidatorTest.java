package dev.mmartins.jwtverifyapi.unit;

import dev.mmartins.jwtverifyapi.application.RoleValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoleValidatorTest {

    @Test
    void shouldReturnFalseIfIsNotAValidRole() {
        RoleValidator validator = new RoleValidator("Manager");
        assertFalse(validator.isValid());
    }

    @Test
    void shouldReturnTrueIfIsAValidRole() {
        RoleValidator validator = new RoleValidator("Admin");
        assertTrue(validator.isValid());
    }
}
