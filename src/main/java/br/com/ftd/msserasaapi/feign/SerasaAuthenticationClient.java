package br.com.ftd.msserasaapi.feign;

import br.com.ftd.msserasaapi.model.SerasaTokenClientResponse;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "serasa-auth-client",
        url = "${serasa.auth-base-url}",
        configuration = SerasaAuthFeignConfig.class
)
public interface SerasaAuthenticationClient {

    @PostMapping(value = "${serasa.token-path}", consumes = "application/json")
    SerasaTokenClientResponse fetchToken(
            @RequestHeader("Authorization") String authorization,
            @RequestBody Map<String, Object> body
    );
}
