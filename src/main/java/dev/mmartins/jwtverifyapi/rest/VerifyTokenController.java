package dev.mmartins.jwtverifyapi.rest;

import dev.mmartins.jwtverifyapi.application.usecase.VerifyTokenUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token/verify")
public class VerifyTokenController {
    private final VerifyTokenUseCase useCase;

    public VerifyTokenController(VerifyTokenUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> verify(@RequestBody final TokenRequest payload) {
        final boolean result = useCase.execute(payload);
        return ResponseEntity.ok(new TokenResponse(result));
    }
}
