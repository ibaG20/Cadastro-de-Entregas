package com.luizalabs.entregas.dto;

import java.time.LocalDate;

import com.luizalabs.entregas.config.UniqueCpf;
import com.luizalabs.entregas.model.Endereco;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class EntregaDto {

    private Long id; // Não precisa de validação aqui, pois o ID será gerado ou passado no contexto correto.

    @Min(value = 1, message = "A quantidade de pacotes deve ser no mínimo 1")
    @NotNull(message = "A quantidade de pacotes é obrigatória")
    private Integer quantidadePacotes;

    @NotNull(message = "A data limite de entrega é obrigatória")
    @Future(message = "A data limite de entrega deve ser uma data futura")
    private LocalDate dataLimiteEntrega;

    @NotBlank(message = "O nome do cliente é obrigatório")
    private String nomeCliente;

    @NotBlank(message = "O CPF do cliente é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato 000.000.000-00")
    @UniqueCpf
    private String cpfCliente;

    @NotNull(message = "O endereço de destino é obrigatório")
    private Endereco enderecoDestino;
}
