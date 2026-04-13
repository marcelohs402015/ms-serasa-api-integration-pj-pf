package br.com.ftd.msserasaapi.model;

import javax.validation.constraints.NotNull;

public record TokenRequest(@NotNull TokenType tipo) {
}
