package dev.mmartins.jwtverifyapi.application.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mmartins.jwtverifyapi.application.ClaimValidator;
import dev.mmartins.jwtverifyapi.application.NameValidator;
import dev.mmartins.jwtverifyapi.application.RoleValidator;
import dev.mmartins.jwtverifyapi.application.SeedValidator;


public class JwtValidator implements ClaimValidator {
    private final ClaimValidator seedValidator;
    private final ClaimValidator nameValidator;
    private final ClaimValidator roleValidator;

    public JwtValidator(@JsonProperty("Role") final String role, @JsonProperty("Seed") final Integer seed, @JsonProperty("Name") final String name) {
        this.seedValidator = new SeedValidator(seed);
        this.roleValidator = new RoleValidator(role);
        this.nameValidator = new NameValidator(name);
    }

    public boolean isValid() {
        return this.seedValidator.isValid() && this.nameValidator.isValid() && roleValidator.isValid();
    }
}
