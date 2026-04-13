package br.com.ftd.msserasaapi.model;

import java.time.Instant;

public record TokenResponse(
        String accessToken,
        String tokenType,
        long expiresIn,
        Instant expiresAt,
        TokenType context
) {
}
