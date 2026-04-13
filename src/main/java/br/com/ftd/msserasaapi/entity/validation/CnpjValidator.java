package br.com.ftd.msserasaapi.entity.validation;

import java.util.Locale;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CnpjValidator implements ConstraintValidator<CNPJ, String> {

    private static final int[] FIRST_WEIGHTS  = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] SECOND_WEIGHTS = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }
        String normalized = normalize(value);
        if (!normalized.matches("[A-Z0-9]{14}")) {
            return false;
        }
        if (!Character.isDigit(normalized.charAt(12)) || !Character.isDigit(normalized.charAt(13))) {
            return false;
        }
        if (normalized.chars().distinct().count() == 1) {
            return false;
        }
        int firstDigit = calculateDigit(normalized.substring(0, 12), FIRST_WEIGHTS);
        int secondDigit = calculateDigit(normalized.substring(0, 12) + firstDigit, SECOND_WEIGHTS);
        return firstDigit == normalized.charAt(12) - '0'
                && secondDigit == normalized.charAt(13) - '0';
    }

    private String normalize(String value) {
        return value.replaceAll("[^A-Za-z0-9]", "").toUpperCase(Locale.ROOT);
    }

    private int calculateDigit(String base, int[] weights) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += toValue(base.charAt(i)) * weights[i];
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    private int toValue(char character) {
        return Character.isDigit(character) ? character - '0' : character - 48;
    }
}
