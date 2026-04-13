package br.com.ftd.msserasaapi.entity.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<CPF, String> {

    private static final int[] FIRST_WEIGHTS  = {10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] SECOND_WEIGHTS = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }
        String normalized = value.replaceAll("[^0-9]", "");
        if (normalized.length() != 11) {
            return false;
        }
        if (normalized.chars().distinct().count() == 1) {
            return false;
        }
        int firstDigit = calculateDigit(normalized.substring(0, 9), FIRST_WEIGHTS);
        int secondDigit = calculateDigit(normalized.substring(0, 10), SECOND_WEIGHTS);
        return firstDigit == Character.getNumericValue(normalized.charAt(9))
                && secondDigit == Character.getNumericValue(normalized.charAt(10));
    }

    private int calculateDigit(String base, int[] weights) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += Character.getNumericValue(base.charAt(i)) * weights[i];
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }
}
