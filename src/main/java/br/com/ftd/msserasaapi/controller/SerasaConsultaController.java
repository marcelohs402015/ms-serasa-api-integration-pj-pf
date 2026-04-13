package br.com.ftd.msserasaapi.controller;

import br.com.ftd.msserasaapi.model.ConsultaRequest;
import br.com.ftd.msserasaapi.service.ConsultaService;
import com.fasterxml.jackson.databind.JsonNode;
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
public class SerasaConsultaController {

    private final ConsultaService consultaService;

    @PostMapping("/consultas")
    public ResponseEntity<JsonNode> consultar(@Valid @RequestBody ConsultaRequest request) {
        return ResponseEntity.ok(consultaService.consultar(request));
    }
}
