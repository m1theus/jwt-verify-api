package dev.mmartins.jwtverifyapi.application;

public class SeedValidator implements ClaimValidator {
    private final Integer seed;

    public SeedValidator(final Integer seed) {
        this.seed = seed;
    }

    @Override
    public boolean isValid() {
        for (var i = 2; i < Math.sqrt(this.seed); i++) {
            if (this.seed % i == 0) return false;
        }
        return this.seed > 1;
    }
}
