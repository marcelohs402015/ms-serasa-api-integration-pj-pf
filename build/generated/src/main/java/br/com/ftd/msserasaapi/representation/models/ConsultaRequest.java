package br.com.ftd.msserasaapi.representation.models;

import java.net.URI;
import java.util.Objects;
import br.com.ftd.msserasaapi.representation.models.ReportFeature;
import br.com.ftd.msserasaapi.representation.models.TipoConsulta;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ConsultaRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.8.0")
public class ConsultaRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private TipoConsulta tipo;

  private String documento;

  @Valid
  private List<ReportFeature> features = new ArrayList<>();

  public ConsultaRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ConsultaRequest(TipoConsulta tipo, String documento, List<ReportFeature> features) {
    this.tipo = tipo;
    this.documento = documento;
    this.features = features;
  }

  public ConsultaRequest tipo(TipoConsulta tipo) {
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

  public ConsultaRequest documento(String documento) {
    this.documento = documento;
    return this;
  }

  /**
   * CPF (PF) ou CNPJ (PJ), sem mascara.
   * @return documento
   */
  @NotNull 
  @Schema(name = "documento", example = "03994975000170", description = "CPF (PF) ou CNPJ (PJ), sem mascara.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("documento")
  public String getDocumento() {
    return documento;
  }

  public void setDocumento(String documento) {
    this.documento = documento;
  }

  public ConsultaRequest features(List<ReportFeature> features) {
    this.features = features;
    return this;
  }

  public ConsultaRequest addFeaturesItem(ReportFeature featuresItem) {
    if (this.features == null) {
      this.features = new ArrayList<>();
    }
    this.features.add(featuresItem);
    return this;
  }

  /**
   * Lista de opcoes de emissao de relatorio. Consulte as listas separadas em ReportFeaturePf e ReportFeaturePj para escopo especifico. 
   * @return features
   */
  @NotNull @Valid @Size(min = 1) 
  @Schema(name = "features", description = "Lista de opcoes de emissao de relatorio. Consulte as listas separadas em ReportFeaturePf e ReportFeaturePj para escopo especifico. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("features")
  public List<ReportFeature> getFeatures() {
    return features;
  }

  public void setFeatures(List<ReportFeature> features) {
    this.features = features;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConsultaRequest consultaRequest = (ConsultaRequest) o;
    return Objects.equals(this.tipo, consultaRequest.tipo) &&
        Objects.equals(this.documento, consultaRequest.documento) &&
        Objects.equals(this.features, consultaRequest.features);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tipo, documento, features);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConsultaRequest {\n");
    sb.append("    tipo: ").append(toIndentedString(tipo)).append("\n");
    sb.append("    documento: ").append(toIndentedString(documento)).append("\n");
    sb.append("    features: ").append(toIndentedString(features)).append("\n");
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

