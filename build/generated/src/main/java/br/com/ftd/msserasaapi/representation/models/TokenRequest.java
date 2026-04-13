package br.com.ftd.msserasaapi.representation.models;

import java.net.URI;
import java.util.Objects;
import br.com.ftd.msserasaapi.representation.models.TipoConsulta;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * TokenRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.8.0")
public class TokenRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private TipoConsulta tipo;

  public TokenRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TokenRequest(TipoConsulta tipo) {
    this.tipo = tipo;
  }

  public TokenRequest tipo(TipoConsulta tipo) {
    this.tipo = tipo;
    return this;
  }

  /**
   * Get tipo
   * @return tipo
   */
  @NotNull @Valid 
  @Schema(name = "tipo", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tipo")
  public TipoConsulta getTipo() {
    return tipo;
  }

  public void setTipo(TipoConsulta tipo) {
    this.tipo = tipo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TokenRequest tokenRequest = (TokenRequest) o;
    return Objects.equals(this.tipo, tokenRequest.tipo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tipo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TokenRequest {\n");
    sb.append("    tipo: ").append(toIndentedString(tipo)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

