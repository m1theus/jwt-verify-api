package dev.m1theus.jwtverifyapi.entrypoint.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token/verify")
public class VerifyTokenController {

    @PostMapping
    public ResponseEntity<TokenResponse> verify(@RequestBody final TokenRequest payload) {
        return ResponseEntity.ok(new TokenResponse(true));
    }
}
