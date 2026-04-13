package br.com.ftd.msserasaapi.service;

import br.com.ftd.msserasaapi.config.SerasaProperties;
import br.com.ftd.msserasaapi.feign.SerasaPjReportClient;
import br.com.ftd.msserasaapi.model.ConsultaRequest;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultaPjStrategy implements ConsultaStrategy {

    private final SerasaPjReportClient serasaPjReportClient;
    private final SerasaProperties serasaProperties;

    private static final String DEFAULT_REPORT_NAME = "RELATORIO_INTERMEDIARIO_TOP_SCORE_PJ";

    @Override
    public JsonNode executar(ConsultaRequest request, String bearerToken) {
        return serasaPjReportClient.fetchReport(
                bearerToken,
                request.documento(),
                DEFAULT_REPORT_NAME,
                serasaProperties.retailerDocumentId(),
                serasaProperties.costCenter()
        );
    }
}
