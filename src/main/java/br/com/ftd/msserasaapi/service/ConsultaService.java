package br.com.ftd.msserasaapi.service;

import br.com.ftd.msserasaapi.exception.IntegrationException;
import br.com.ftd.msserasaapi.model.ConsultaRequest;
import br.com.ftd.msserasaapi.model.TokenType;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final SerasaTokenService serasaTokenService;
    private final Map<TokenType, ConsultaStrategy> strategies;

    public JsonNode consultar(ConsultaRequest request) {
        ConsultaStrategy strategy = strategies.get(request.tipo());
        if (strategy == null) {
            throw new IntegrationException("TIPO_NAO_SUPORTADO", "Tipo de consulta nao suportado: " + request.tipo(), 400);
        }
        String bearerToken = "Bearer " + serasaTokenService.getToken(request.tipo()).accessToken();
        return strategy.executar(request, bearerToken);
    }
}
