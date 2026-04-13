package br.com.ftd.msserasaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ConsultaResponse(
        String numeroConsulta,
        String dataConsulta,
        Object dadosRelatorio
) {
}
