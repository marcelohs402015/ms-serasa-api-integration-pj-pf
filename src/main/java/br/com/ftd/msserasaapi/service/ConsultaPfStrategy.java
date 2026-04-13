package br.com.ftd.msserasaapi.service;

import br.com.ftd.msserasaapi.feign.SerasaPfReportClient;
import br.com.ftd.msserasaapi.model.ConsultaRequest;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultaPfStrategy implements ConsultaStrategy {

    private final SerasaPfReportClient serasaPfReportClient;

    private static final String DEFAULT_REPORT_NAME = "RELATORIO_INTERMEDIARIO_TOP_SCORE_PF";

    @Override
    public JsonNode executar(ConsultaRequest request, String bearerToken) {
        return serasaPfReportClient.fetchReport(
                bearerToken,
                request.documento(),
                DEFAULT_REPORT_NAME
        );
    }
}
