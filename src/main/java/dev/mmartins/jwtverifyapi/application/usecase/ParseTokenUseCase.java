package dev.mmartins.jwtverifyapi.application.usecase;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mmartins.jwtverifyapi.application.domain.JwtValidator;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ParseTokenUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ParseTokenUseCase.class);
    private final ObjectMapper objectMapper;

    public ParseTokenUseCase() {
        this.objectMapper = new ObjectMapper();
    }

    public Optional<JwtValidator> execute(final String token) {
        try {
            final var jwtEncodedStr = token.split("\\.")[1];
            final var jwtPayloadJson = new String(Base64.decodeBase64(jwtEncodedStr));

            return Optional.of(this.objectMapper
                    .reader()
                    .forType(JwtValidator.class)
                    .readValue(jwtPayloadJson));
        } catch (final Exception exception) {
            logger.error("c=ParseTokenUseCase m=execute msg=failed_to_parse_jwt_token error={}", exception.getLocalizedMessage());
            return Optional.empty();
        }
    }
}
