package com.luizalabs.entregas.model;

import java.time.LocalDate;

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

    @Column(nullable = false)
    private String cpfCliente;

    @Embedded
    private Endereco enderecoDestino;


    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Endereco {

        @Column(nullable = false)
        private String cep;

        @Column(nullable = false)
        private String cef;

        @Column(nullable = false)
        private String cidade;

        @Column(nullable = false)
        private String bairro;

        @Column(nullable = false)
        private String rua;

        @Column(nullable = false)
        private String numero;

        private String complemento;

    }
}