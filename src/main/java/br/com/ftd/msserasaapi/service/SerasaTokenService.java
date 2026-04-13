package br.com.ftd.msserasaapi.service;

import br.com.ftd.msserasaapi.model.TokenResponse;
import br.com.ftd.msserasaapi.model.TokenType;

public interface SerasaTokenService {

    TokenResponse getToken(TokenType tokenType);
}
