package br.com.ftd.msserasaapi.feign;

import br.com.ftd.msserasaapi.exception.IntegrationException;
import feign.Logger;
import feign.Util;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerasaReportFeignConfig {

    @Bean
    public Logger.Level reportFeignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder serasaReportErrorDecoder() {
        return this::decodeError;
    }

    private Exception decodeError(String methodKey, Response response) {
        String responseBody = "";
        if (response.body() != null) {
            try {
                responseBody = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
            } catch (IOException ignored) {
                responseBody = "";
            }
        }
        String message = "Falha ao consultar relatorio na Serasa";
        if (!responseBody.isBlank()) {
            message = message + ": " + responseBody;
        }
        return new IntegrationException("SERASA_REPORT_ERROR", message, response.status());
    }
}
