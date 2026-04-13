# ms-serasa-api

API interna para consulta de relatorios Serasa PF e PJ com autenticacao IAM, validacao de documento e padronizacao de erros.

## Stack
- Java 17
- Spring Boot 2.7.x
- Spring Cloud OpenFeign
- Bean Validation
- Gradle

## Endpoint interno
### Consultar relatorio
- Metodo: `POST`
- URL: `http://localhost:8080/ms-serasa-api/v1/serasa/consultas`
- Content-Type: `application/json`

Request:
```json
{
  "tipo": "PF",
  "documento": "00047668148",
  "features": ["DADOS_CADASTRAIS"]
}
```

Campos:
- `tipo`: `PF` ou `PJ`
- `documento`: CPF/CNPJ sem formatacao
- `features`: lista de blocos desejados (entrada padrao da API interna)

## Como a API funciona internamente
1. Recebe request no controller.
2. Valida payload (campos obrigatorios + CPF/CNPJ por digito verificador).
3. Busca token IAM da Serasa (cache em memoria).
4. Seleciona strategy por tipo (`PF` ou `PJ`).
5. Executa consulta externa via Feign.
6. Retorna o JSON da Serasa para o consumidor.

## Integracao Serasa
### Token IAM
- `POST /security/iam/v1/client-identities/login`
- Header: `Authorization: Basic <base64(clientId:clientSecret)>`

### PF
- `GET /credit-services/person-information-report/v1/creditreport`
- Headers:
  - `Authorization: Bearer <token>`
  - `X-Document-Id: <cpf>`
- Query:
  - `reportName=RELATORIO_INTERMEDIARIO_TOP_SCORE_PF`

### PJ
- `GET /credit-services/business-information-report/v1/reports`
- Headers:
  - `Authorization: Bearer <token>`
  - `X-Document-Id: <cnpj>`
  - `X-Retailer-Document-Id` (opcional)
  - `X-Cost-Center` (opcional)
- Query:
  - `reportName=RELATORIO_INTERMEDIARIO_TOP_SCORE_PJ`

## Documentacao de origem (Serasa)
- [Relatorio Intermediario PF](https://developer.serasaexperian.com.br/api/relatorio-intermediario-pf)
- [Relatorio Intermediario PJ](https://developer.serasaexperian.com.br/api/relatorio-intermediario-pj)

## Opcoes de emissao de relatorio
### PF
- Relatorio padrao implementado:
  - `RELATORIO_INTERMEDIARIO_TOP_SCORE_PF`
- Features opcionais catalogadas pela Serasa (exemplos):
  - `CONSULTAS_A_SERASA`
  - `SCORE_POSITIVO_SCORE_POSITIVO_PME`
  - `PARTICIPACAO_SOCIETARIA`
  - `ANOTACOES_CONSULTAS_SPC`
  - `COMPROMETIMENTO_RENDA`
  - `CAPACIDADE_PAGAMENTO`
  - `HISTORICO_PAGAMENTO`
  - `PONTUALIDADE_PAGAMENTO_PF`
  - `RENDA_ESTIMADA`
  - `RECOMENDACAO_LIMITE_CREDITO`
  - `LOCALIZACAO_PF`
  - `SCORE_FRAUDE_PF`
  - `INDICE_RELACIONAMENTO_MERCADO_SETOR_PF`
  - `SCORE_CURTO_PRAZO`
  - `ALERTA_OBITO`
  - `SCORE_CUSTOMIZADO`
  - `AGRO_SCORE_PF`
  - `AGRO_RENDA_ESTIMADA`
  - `SEGMENTACAO_MOSAIC`
  - `RISCO_CREDITO_PERFIL`
  - `ACHEI_RECHEQUE_PF_DOCUMENTO`
  - `ACHEI_RECHEQUE_PF_BANCO`
  - `ACHEI_RECHEQUE_PF_CMC7`
  - `RENDA_ESTIMADA_PF`
  - `RENDA_MAIS`
  - `INDICADOR_RECUPERACAO_CREDITO_PF`

### PJ
- Relatorio padrao implementado:
  - `RELATORIO_INTERMEDIARIO_TOP_SCORE_PJ`
- Features opcionais catalogadas pela Serasa (exemplos):
  - `QSA`
  - `QSA_COMPLETO`
  - `CONSULTAS_A_SERASA`
  - `SCORE_POSITIVO`
  - `PARTICIPACOES`
  - `LIMITE_CREDITO`
  - `SITUACAO_FISCAL`
  - `MOSAIC_BUSINESS`
  - `AGRO_SCORE_PJ`
  - `FATURAMENTO_RECEBIVEIS`

> Nota: na implementacao atual, `features` ja e recebida pela API interna e validada; uso direto no request externo pode ser expandido na proxima fase conforme contrato final.

## Validacoes e erros
### Validacoes
- `tipo` obrigatorio e valido (`PF`/`PJ`)
- `documento` obrigatorio e valido conforme o tipo
- `features` obrigatorio e nao vazio

### Erros padronizados
- `VALIDATION_ERROR` (400)
- `METHOD_NOT_ALLOWED` (405)
- `SERASA_AUTH_ERROR` / `SERASA_REPORT_ERROR` (erro de integracao)
- `UNEXPECTED_ERROR` (500)

## Configuracao
Arquivo: `src/main/resources/application.yaml`

Variaveis principais:
- `SERASA_CLIENT_ID`
- `SERASA_CLIENT_SECRET`
- `SERASA_AUTH_BASE_URL`
- `SERASA_REPORT_BASE_URL`
- `SERASA_PF_REPORT_PATH`
- `SERASA_PJ_REPORT_PATH`
- `SERASA_RETAILER_DOCUMENT_ID` (opcional)
- `SERASA_COST_CENTER` (opcional)

### Requisito obrigatorio para subir a API
Antes de executar a aplicacao, e obrigatorio definir as variaveis de ambiente:
- `SERASA_CLIENT_ID`
- `SERASA_CLIENT_SECRET`

## Execucao local
```bash
./gradlew.bat bootRun
```

## Testes
```bash
./gradlew.bat test
```
