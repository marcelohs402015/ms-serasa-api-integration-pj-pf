package br.com.ftd.msserasaapi.entity.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DocumentoValidoValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DocumentoValido {

    String message() default "Documento invalido para o tipo informado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
