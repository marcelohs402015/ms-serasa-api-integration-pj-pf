package br.com.ftd.msserasaapi.controller;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@WireMockTest(httpPort = 9090)
@TestPropertySource(properties = {
        "serasa.auth-base-url=http://localhost:9090",
        "serasa.report-base-url=http://localhost:9090",
        "serasa.client-id=test-client-id",
        "serasa.client-secret=test-client-secret"
})
class SerasaConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnReportWhenPfConsultaIsSuccessful() throws Exception {
        stubFor(WireMock.post(urlPathEqualTo("/security/iam/v1/client-identities/login"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                  "accessToken": "mock-token",
                                  "tokenType": "Bearer",
                                  "expiresIn": "3600",
                                  "scope": ["READ", "WRITE"]
                                }
                                """)));

        stubFor(get(urlPathEqualTo("/credit-services/person-information-report/v1/creditreport"))
                .withHeader("X-Document-Id", equalTo("00047668148"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                  "protocolo": "20260409ABC123",
                                  "dataConsulta": "2026-04-09T15:00:00Z",
                                  "dadosCadastrais": {
                                    "nome": "NOME FICTICIO",
                                    "situacaoReceitaFederal": "REGULAR"
                                  }
                                }
                                """)));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/serasa/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "tipo": "PF",
                                  "documento": "00047668148",
                                  "features": ["DADOS_CADASTRAIS", "PENDENCIAS"]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.protocolo").value("20260409ABC123"))
                .andExpect(jsonPath("$.dadosCadastrais.nome").value("NOME FICTICIO"));
    }

    @Test
    void shouldReturnBadRequestWhenPayloadIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/serasa/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "tipo": "PF"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenFeaturesIsEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/serasa/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "tipo": "PF",
                                  "documento": "00047668148",
                                  "features": []
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenPfDocumentIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/serasa/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "tipo": "PF",
                                  "documento": "123",
                                  "features": ["DADOS_CADASTRAIS"]
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"))
                .andExpect(jsonPath("$.message").value(org.hamcrest.Matchers.containsString("documento: Documento invalido para tipo PF")));
    }

    @Test
    void shouldReturnBadRequestWhenTipoIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/serasa/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "tipo": "XX",
                                  "documento": "11222333000181",
                                  "features": ["DADOS_CADASTRAIS"]
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"))
                .andExpect(jsonPath("$.message").value("tipo: valor invalido. Use PF ou PJ"));
    }

    @Test
    void shouldReturnReportWhenPjConsultaIsSuccessful() throws Exception {
        stubFor(WireMock.post(urlPathEqualTo("/security/iam/v1/client-identities/login"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                  "accessToken": "mock-token",
                                  "tokenType": "Bearer",
                                  "expiresIn": "3600",
                                  "scope": ["READ", "WRITE"]
                                }
                                """)));

        stubFor(get(urlPathEqualTo("/credit-services/business-information-report/v1/reports"))
                .withHeader("X-Document-Id", equalTo("11222333000181"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                  "reports": [
                                    {
                                      "reportName": "RELATORIO_INTERMEDIARIO_PJ"
                                    }
                                  ]
                                }
                                """)));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/serasa/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "tipo": "PJ",
                                  "documento": "11222333000181",
                                  "features": ["DADOS_CADASTRAIS"]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reports[0].reportName").value("RELATORIO_INTERMEDIARIO_PJ"));
    }
}
