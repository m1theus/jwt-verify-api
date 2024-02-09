package dev.mmartins.jwtverifyapi.unit.usecase;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.mmartins.jwtverifyapi.application.usecase.ParseTokenUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParseTokenUseCaseTest {
    private ParseTokenUseCase useCase;

    @BeforeEach
    void setUp() {
        this.useCase = new ParseTokenUseCase();
    }

    @Test
    void shouldReturnFalseIfTokenIsInvalid() {
        final String token = JWT
                .create()
                .withClaim("", "u�\\u001d~��Ȕ��\\u001aȎ��Y\\u001bZ[��\\b�ٮB#�#s�C\\u0012\\\"�$�\\u0016�R#�%F������\\u0004\\u0017&\\u0017V��")
                .sign(Algorithm.none());
        assertFalse(useCase.execute(token).isPresent());
    }

    @Test
    void shouldReturnFalseIfTokenHasInvalidClaims() {
        final String token = JWT
                .create()
                .withClaim("Role", "Admin")
                .withClaim("Seed", "2")
                .withClaim("Name", "José Silva")
                .withClaim("COUNTRY", "BR")
                .sign(Algorithm.none());
        assertFalse(useCase.execute(token).isPresent());
    }

    @Test
    void shouldReturnTrueIfTokenHasValidClaims() {
        final String token = JWT
                .create()
                .withClaim("Role", "Admin")
                .withClaim("Seed", "2")
                .withClaim("Name", "José Silva")
                .sign(Algorithm.none());
        assertTrue(useCase.execute(token).isPresent());
    }
}
