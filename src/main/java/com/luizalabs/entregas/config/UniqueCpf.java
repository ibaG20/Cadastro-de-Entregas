package com.luizalabs.entregas.config;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueCpfValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCpf {

    String message() default "Já existe uma entrega cadastrada com este CPF.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
