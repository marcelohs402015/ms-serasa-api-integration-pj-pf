package br.com.ftd.msserasaapi.config;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "serasa")
public record SerasaProperties(
        @NotBlank String authBaseUrl,
        @NotBlank String tokenPath,
        @NotBlank String clientId,
        @NotBlank String clientSecret,
        @Min(10) long refreshWindowSeconds,
        String retailerDocumentId,
        String costCenter
) {

    public String buildBasicAuthorization() {
        String credentials = clientId + ":" + clientSecret;
        return "Basic " + java.util.Base64.getEncoder().encodeToString(credentials.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }
}
