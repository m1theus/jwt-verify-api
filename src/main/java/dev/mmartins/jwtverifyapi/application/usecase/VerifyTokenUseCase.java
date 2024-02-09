package dev.mmartins.jwtverifyapi.application.usecase;

import dev.mmartins.jwtverifyapi.application.domain.JwtValidator;
import dev.mmartins.jwtverifyapi.rest.TokenRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VerifyTokenUseCase {
    private static final Logger logger = LoggerFactory.getLogger(VerifyTokenUseCase.class);
    private final ParseTokenUseCase parseTokenUseCase;

    public VerifyTokenUseCase(final ParseTokenUseCase parseTokenUseCase) {
        this.parseTokenUseCase = parseTokenUseCase;
    }

    public boolean execute(final TokenRequest payload) {
        final Optional<JwtValidator> optionalJwtPayload = parseTokenUseCase.execute(payload.token());
        final var result = optionalJwtPayload.map(JwtValidator::isValid).orElse(Boolean.FALSE);
        logger.info("c=VerifyTokenUseCase m=execute is_jwt_valid={}", result);
        return result;
    }
}
