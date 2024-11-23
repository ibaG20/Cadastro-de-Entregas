package com.luizalabs.entregas.dto;

import java.time.LocalDate;

import com.luizalabs.entregas.model.Entrega.Endereco;

import lombok.Data;

@Data
public class EntregaDto {

    private Long id;
    private int quantidadePacotes;
    private LocalDate dataLimiteEntrega;
    private String nomeCliente;
    private String cpfCliente;
    private Endereco enderecoDestino;

}
