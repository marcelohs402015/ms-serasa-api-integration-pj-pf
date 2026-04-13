package br.com.ftd.msserasaapi.controller;

import br.com.ftd.msserasaapi.model.TokenRequest;
import br.com.ftd.msserasaapi.model.TokenResponse;
import br.com.ftd.msserasaapi.service.SerasaTokenService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/serasa")
@RequiredArgsConstructor
public class SerasaTokenController {

    private final SerasaTokenService serasaTokenService;

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> generateToken(@Valid @RequestBody TokenRequest request) {
        return ResponseEntity.ok(serasaTokenService.getToken(request.tipo()));
    }
}
