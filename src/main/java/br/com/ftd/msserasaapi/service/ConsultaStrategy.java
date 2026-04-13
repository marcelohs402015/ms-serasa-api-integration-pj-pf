package br.com.ftd.msserasaapi.service;

import br.com.ftd.msserasaapi.model.ConsultaRequest;
import com.fasterxml.jackson.databind.JsonNode;

public interface ConsultaStrategy {

    JsonNode executar(ConsultaRequest request, String bearerToken);
}
