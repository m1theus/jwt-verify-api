package dev.mmartins.jwtverifyapi.unit.usecase;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.mmartins.jwtverifyapi.application.usecase.ParseTokenUseCase;
import dev.mmartins.jwtverifyapi.application.usecase.VerifyTokenUseCase;
import dev.mmartins.jwtverifyapi.rest.TokenRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerifyTokenUseCaseTest {
    private VerifyTokenUseCase useCase;

    @BeforeEach
    void setUp() {
        this.useCase = new VerifyTokenUseCase(new ParseTokenUseCase());
    }

    @Test
    void shouldReturnFalseIfTokenIsInvalid() {
        final String token = JWT
                .create()
                .withClaim("", "u�\\u001d~��Ȕ��\\u001aȎ��Y\\u001bZ[��\\b�ٮB#�#s�C\\u0012\\\"�$�\\u0016�R#�%F������\\u0004\\u0017&\\u0017V��")
                .sign(Algorithm.none());

        final boolean result = useCase.execute(new TokenRequest(token));
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTokenHasInvalidRole() {
        final String token = JWT
                .create()
                .withClaim("Role", "Manager")
                .withClaim("Seed", "2")
                .withClaim("Name", "José Silva")
                .sign(Algorithm.none());

        final boolean result = useCase.execute(new TokenRequest(token));
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTokenHasInvalidSeed() {
        final String token = JWT
                .create()
                .withClaim("Role", "Admin")
                .withClaim("Seed", "1")
                .withClaim("Name", "José Silva")
                .sign(Algorithm.none());

        final boolean result = useCase.execute(new TokenRequest(token));
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTokenHasInvalidName() {
        final String token = JWT
                .create()
                .withClaim("Role", "Admin")
                .withClaim("Seed", "1")
                .withClaim("Name", "M4r14 S1lv4")
                .sign(Algorithm.none());

        final boolean result = useCase.execute(new TokenRequest(token));
        assertFalse(result);
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

        final boolean result = useCase.execute(new TokenRequest(token));
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTokenIsValid() {
        final String token = JWT
                .create()
                .withClaim("Role", "Admin")
                .withClaim("Seed", "2")
                .withClaim("Name", "José Silva")
                .sign(Algorithm.none());

        final boolean result = useCase.execute(new TokenRequest(token));
        assertTrue(result);
    }
}
