package br.com.ftd.msserasaapi.feign;

import br.com.ftd.msserasaapi.exception.IntegrationException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerasaAuthFeignConfig {

    @Bean
    public ErrorDecoder serasaAuthErrorDecoder() {
        return this::decodeError;
    }

    private Exception decodeError(String methodKey, Response response) {
        return new IntegrationException("SERASA_AUTH_ERROR", "Falha ao autenticar na Serasa", response.status());
    }
}
