package com.luizalabs.entregas.model;

import java.time.LocalDate;

import com.luizalabs.entregas.dto.EntregaDto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantidadePacotes;

    @Column(nullable = false)
    private LocalDate dataLimiteEntrega;

    @Column(nullable = false)
    private String nomeCliente;

    @Column(nullable = false, unique = true)
    private String cpfCliente;

    @Embedded
    private Endereco enderecoDestino;

    public Entrega(EntregaDto entregaDto) {
        this.id = entregaDto.getId();
        this.quantidadePacotes = entregaDto.getQuantidadePacotes();
        this.dataLimiteEntrega = entregaDto.getDataLimiteEntrega();
        this.nomeCliente = entregaDto.getNomeCliente();
        this.cpfCliente = entregaDto.getCpfCliente();
        this.enderecoDestino = entregaDto.getEnderecoDestino();
    }
}