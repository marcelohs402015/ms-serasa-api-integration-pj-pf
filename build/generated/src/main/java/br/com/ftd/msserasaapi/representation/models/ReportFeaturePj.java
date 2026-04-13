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
 * Lista separada de opcoes de emissao para escopo PJ.
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.8.0")
public enum ReportFeaturePj {
  
  DADOS_CADASTRAIS("DADOS_CADASTRAIS"),
  
  QSA("QSA"),
  
  QSA_COMPLETO("QSA_COMPLETO"),
  
  CONSULTAS_A_SERASA("CONSULTAS_A_SERASA"),
  
  SCORE_POSITIVO("SCORE_POSITIVO"),
  
  PARTICIPACOES("PARTICIPACOES"),
  
  ANOTACOES_CONSULTAS_SPC("ANOTACOES_CONSULTAS_SPC"),
  
  LIMITE_CREDITO("LIMITE_CREDITO"),
  
  GASTO_ESTIMADO_POSITIVO("GASTO_ESTIMADO_POSITIVO"),
  
  PONTUALIDADE_PAGAMENTO("PONTUALIDADE_PAGAMENTO"),
  
  CAPACIDADE_MENSAL_PAGAMENTO("CAPACIDADE_MENSAL_PAGAMENTO"),
  
  FATURAMENTO_ESTIMADO_POSITIVO("FATURAMENTO_ESTIMADO_POSITIVO"),
  
  RECOMENDACAO_LIMITE_CREDITO("RECOMENDACAO_LIMITE_CREDITO"),
  
  PERFIL_FINANCEIRO("PERFIL_FINANCEIRO"),
  
  INDICE_RELACIONAMENTO_MERCADO_SETOR_PJ("INDICE_RELACIONAMENTO_MERCADO_SETOR_PJ"),
  
  RISCO_NOVAS_EMPRESAS("RISCO_NOVAS_EMPRESAS"),
  
  SCORE_FRAUDE_PJ("SCORE_FRAUDE_PJ"),
  
  SCORE_CUSTOMIZADO("SCORE_CUSTOMIZADO"),
  
  INDICADORES_RECEBIVEIS_SEM_CONSENTIMENTO("INDICADORES_RECEBIVEIS_SEM_CONSENTIMENTO"),
  
  DIVIDAS_ORGAOS_PUBLICOS("DIVIDAS_ORGAOS_PUBLICOS"),
  
  LOCALIZACAO_PJ("LOCALIZACAO_PJ"),
  
  CONSULTAS_DETALHADAS_SERASA("CONSULTAS_DETALHADAS_SERASA"),
  
  SCORE_DE_SOCIO_PJ("SCORE_DE_SOCIO_PJ"),
  
  ANOTACOES_CONSULTAS_SPC_SOCIOS_ADMINISTRADORES("ANOTACOES_CONSULTAS_SPC_SOCIOS_ADMINISTRADORES"),
  
  SITUACAO_FISCAL("SITUACAO_FISCAL"),
  
  COMPORTAMENTO_PAGAMENTO_SETOR("COMPORTAMENTO_PAGAMENTO_SETOR"),
  
  MOSAIC_BUSINESS("MOSAIC_BUSINESS"),
  
  SCORE_DE_CREDITO_SETORIAL("SCORE_DE_CREDITO_SETORIAL"),
  
  HISTORICO_DE_PAGAMENTO_COMERCIAL_INTERMEDIARIO("HISTORICO_DE_PAGAMENTO_COMERCIAL_INTERMEDIARIO"),
  
  HISTORICO_DE_PAGAMENTO_FINANCEIRO_INTERMEDIARIO_CONCENTRE("HISTORICO_DE_PAGAMENTO_FINANCEIRO_INTERMEDIARIO_CONCENTRE"),
  
  MAIS_ANOTACOES("MAIS_ANOTACOES"),
  
  SCORE_EMPRESA_E_SETOR("SCORE_EMPRESA_E_SETOR"),
  
  VENDAS_CARTAO("VENDAS_CARTAO"),
  
  SCORE_LONGEVIDADE_EMPRESA("SCORE_LONGEVIDADE_EMPRESA"),
  
  INDICADOR_RECUPERACAO_CREDITO_PJ("INDICADOR_RECUPERACAO_CREDITO_PJ"),
  
  AGRO_SCORE_PJ("AGRO_SCORE_PJ"),
  
  FATURAMENTO_RECEBIVEIS("FATURAMENTO_RECEBIVEIS");

  private String value;

  ReportFeaturePj(String value) {
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
  public static ReportFeaturePj fromValue(String value) {
    for (ReportFeaturePj b : ReportFeaturePj.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

