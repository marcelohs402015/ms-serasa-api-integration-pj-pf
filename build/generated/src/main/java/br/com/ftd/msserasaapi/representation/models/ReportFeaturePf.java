package br.com.ftd.msserasaapi.representation.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Lista separada de opcoes de emissao para escopo PF.
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.8.0")
public enum ReportFeaturePf {
  
  DADOS_CADASTRAIS("DADOS_CADASTRAIS"),
  
  CONSULTAS_A_SERASA("CONSULTAS_A_SERASA"),
  
  SCORE_POSITIVO_SCORE_POSITIVO_PME("SCORE_POSITIVO_SCORE_POSITIVO_PME"),
  
  PARTICIPACAO_SOCIETARIA("PARTICIPACAO_SOCIETARIA"),
  
  ANOTACOES_CONSULTAS_SPC("ANOTACOES_CONSULTAS_SPC"),
  
  COMPROMETIMENTO_RENDA("COMPROMETIMENTO_RENDA"),
  
  CAPACIDADE_PAGAMENTO("CAPACIDADE_PAGAMENTO"),
  
  HISTORICO_PAGAMENTO("HISTORICO_PAGAMENTO"),
  
  PONTUALIDADE_PAGAMENTO_PF("PONTUALIDADE_PAGAMENTO_PF"),
  
  RENDA_ESTIMADA("RENDA_ESTIMADA"),
  
  RECOMENDACAO_LIMITE_CREDITO("RECOMENDACAO_LIMITE_CREDITO"),
  
  LOCALIZACAO_PF("LOCALIZACAO_PF"),
  
  SCORE_FRAUDE_PF("SCORE_FRAUDE_PF"),
  
  INDICE_RELACIONAMENTO_MERCADO_SETOR_PF("INDICE_RELACIONAMENTO_MERCADO_SETOR_PF"),
  
  SCORE_CURTO_PRAZO("SCORE_CURTO_PRAZO"),
  
  ALERTA_OBITO("ALERTA_OBITO"),
  
  AGRO_SCORE_PF("AGRO_SCORE_PF"),
  
  AGRO_RENDA_ESTIMADA("AGRO_RENDA_ESTIMADA"),
  
  SEGMENTACAO_MOSAIC("SEGMENTACAO_MOSAIC"),
  
  RISCO_CREDITO_PERFIL("RISCO_CREDITO_PERFIL"),
  
  ACHEI_RECHEQUE_PF_DOCUMENTO("ACHEI_RECHEQUE_PF_DOCUMENTO"),
  
  ACHEI_RECHEQUE_PF_BANCO("ACHEI_RECHEQUE_PF_BANCO"),
  
  ACHEI_RECHEQUE_PF_CMC7("ACHEI_RECHEQUE_PF_CMC7"),
  
  RENDA_ESTIMADA_PF("RENDA_ESTIMADA_PF"),
  
  RENDA_MAIS("RENDA_MAIS"),
  
  INDICADOR_RECUPERACAO_CREDITO_PF("INDICADOR_RECUPERACAO_CREDITO_PF");

  private String value;

  ReportFeaturePf(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ReportFeaturePf fromValue(String value) {
    for (ReportFeaturePf b : ReportFeaturePf.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

