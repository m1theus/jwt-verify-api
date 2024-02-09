package dev.mmartins.jwtverifyapi.application;

import java.util.Arrays;

public class RoleValidator implements ClaimValidator {
    private final String role;

    public RoleValidator(final String role) {
        this.role = role;
    }

    @Override
    public boolean isValid() {
        return Arrays.asList(new String[]{"Admin", "Member", "External"}).contains(this.role);
    }
}
