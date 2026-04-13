package br.com.ftd.msserasaapi.service;

import br.com.ftd.msserasaapi.config.SerasaProperties;
import br.com.ftd.msserasaapi.feign.SerasaAuthenticationClient;
import br.com.ftd.msserasaapi.model.SerasaTokenClientResponse;
import br.com.ftd.msserasaapi.model.TokenResponse;
import br.com.ftd.msserasaapi.model.TokenType;
import java.time.Instant;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SerasaTokenServiceImpl implements SerasaTokenService {

    private final SerasaAuthenticationClient serasaAuthenticationClient;
    private final SerasaProperties serasaProperties;
    private volatile CachedToken cachedToken;

    @Override
    public TokenResponse getToken(TokenType tokenType) {
        CachedToken currentToken = cachedToken;
        if (isTokenUsable(currentToken)) {
            return currentToken.response(tokenType);
        }
        synchronized (this) {
            CachedToken synchronizedToken = cachedToken;
            if (isTokenUsable(synchronizedToken)) {
                return synchronizedToken.response(tokenType);
            }
            CachedToken newToken = requestNewToken();
            cachedToken = newToken;
            return newToken.response(tokenType);
        }
    }

    private CachedToken requestNewToken() {
        SerasaTokenClientResponse externalResponse = serasaAuthenticationClient.fetchToken(serasaProperties.buildBasicAuthorization(), Map.of());
        long expiresInSeconds = Long.parseLong(externalResponse.expiresIn());
        Instant expiresAt = Instant.now().plusSeconds(expiresInSeconds);
        return new CachedToken(externalResponse.accessToken(), externalResponse.tokenType(), expiresInSeconds, expiresAt);
    }

    private boolean isTokenUsable(CachedToken token) {
        if (token == null) {
            return false;
        }
        Instant refreshAt = token.expiresAt().minusSeconds(serasaProperties.refreshWindowSeconds());
        return Instant.now().isBefore(refreshAt);
    }

    private record CachedToken(String accessToken, String tokenType, long expiresIn, Instant expiresAt) {
        private TokenResponse response(TokenType tokenTypeContext) {
            return new TokenResponse(accessToken, tokenType, expiresIn, expiresAt, tokenTypeContext);
        }
    }
}
