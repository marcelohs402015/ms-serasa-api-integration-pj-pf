package br.com.ftd.msserasaapi.model;

import br.com.ftd.msserasaapi.entity.validation.DocumentoValido;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@DocumentoValido
public record ConsultaRequest(
        @NotNull TokenType tipo,
        @NotBlank String documento,
        @NotEmpty List<String> features
) {

    public boolean isPf() {
        return TokenType.PF == tipo;
    }
}
