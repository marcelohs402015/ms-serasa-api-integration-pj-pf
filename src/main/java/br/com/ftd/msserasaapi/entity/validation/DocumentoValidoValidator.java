package br.com.ftd.msserasaapi.entity.validation;

import br.com.ftd.msserasaapi.model.ConsultaRequest;
import br.com.ftd.msserasaapi.model.TokenType;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DocumentoValidoValidator implements ConstraintValidator<DocumentoValido, ConsultaRequest> {

    private final CpfValidator cpfValidator = new CpfValidator();
    private final CnpjValidator cnpjValidator = new CnpjValidator();

    @Override
    public boolean isValid(ConsultaRequest value, ConstraintValidatorContext context) {
        if (value == null || value.tipo() == null || value.documento() == null || value.documento().isBlank()) {
            return true;
        }
        boolean isValid;
        if (value.tipo() == TokenType.PF) {
            isValid = cpfValidator.isValid(value.documento(), context);
        } else if (value.tipo() == TokenType.PJ) {
            isValid = cnpjValidator.isValid(value.documento(), context);
        } else {
            isValid = false;
        }
        if (isValid) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Documento invalido para tipo " + value.tipo())
                .addPropertyNode("documento")
                .addConstraintViolation();
        return false;
    }
}
