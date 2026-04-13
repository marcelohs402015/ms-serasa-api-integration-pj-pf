package br.com.ftd.msserasaapi.feign;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "serasa-pj-report-client",
        url = "${serasa.report-base-url}",
        configuration = SerasaReportFeignConfig.class
)
public interface SerasaPjReportClient {

    @GetMapping("${serasa.pj-report-path}")
    JsonNode fetchReport(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("X-Document-Id") String documentId,
            @RequestParam("reportName") String reportName,
            @RequestHeader(value = "X-Retailer-Document-Id", required = false) String retailerDocumentId,
            @RequestHeader(value = "X-Cost-Center", required = false) String costCenter
    );
}
