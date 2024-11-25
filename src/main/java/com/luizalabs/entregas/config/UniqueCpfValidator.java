package com.luizalabs.entregas.config;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.luizalabs.entregas.repository.EntregaRepository;

public class UniqueCpfValidator implements ConstraintValidator<UniqueCpf, String> {

    @Autowired
    private EntregaRepository entregaRepository;

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        return cpf != null && !entregaRepository.existsByCpfCliente(cpf);
    }
}
