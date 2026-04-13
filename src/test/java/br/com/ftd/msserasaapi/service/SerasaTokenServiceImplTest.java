package br.com.ftd.msserasaapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.ftd.msserasaapi.config.SerasaProperties;
import br.com.ftd.msserasaapi.feign.SerasaAuthenticationClient;
import br.com.ftd.msserasaapi.model.SerasaTokenClientResponse;
import br.com.ftd.msserasaapi.model.TokenResponse;
import br.com.ftd.msserasaapi.model.TokenType;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SerasaTokenServiceImplTest {

    @Mock
    private SerasaAuthenticationClient serasaAuthenticationClient;

    private static final String CLIENT_ID = "test-client-id";
    private static final String CLIENT_SECRET = "test-client-secret";

    @Test
    void shouldReuseCachedTokenWhenStillValid() {
        SerasaProperties serasaProperties = new SerasaProperties(
                "https://uat-api.serasaexperian.com.br",
                "/security/iam/v1/client-identities/login",
                CLIENT_ID,
                CLIENT_SECRET,
                60L,
                null,
                null
        );
        String expectedBasic = serasaProperties.buildBasicAuthorization();
        SerasaTokenServiceImpl serasaTokenService = new SerasaTokenServiceImpl(serasaAuthenticationClient, serasaProperties);
        when(serasaAuthenticationClient.fetchToken(eq(expectedBasic), any()))
                .thenReturn(new SerasaTokenClientResponse("token-123", "Bearer", "3600", List.of("READ", "WRITE")));

        TokenResponse firstResponse = serasaTokenService.getToken(TokenType.PF);
        TokenResponse secondResponse = serasaTokenService.getToken(TokenType.PJ);

        assertEquals("token-123", firstResponse.accessToken());
        assertEquals("token-123", secondResponse.accessToken());
        assertEquals(TokenType.PF, firstResponse.context());
        assertEquals(TokenType.PJ, secondResponse.context());
        verify(serasaAuthenticationClient, times(1)).fetchToken(eq(expectedBasic), any());
    }
}
