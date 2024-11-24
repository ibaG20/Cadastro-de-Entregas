package com.luizalabs.entregas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.luizalabs.entregas.dto.EntregaDto;
import com.luizalabs.entregas.exception.EntregaNotFoundException;
import com.luizalabs.entregas.model.Endereco;
import com.luizalabs.entregas.model.Entrega;
import com.luizalabs.entregas.repository.EntregaRepository;

@ExtendWith(MockitoExtension.class)
public class EntregaServiceTest {

    @InjectMocks
    private EntregaService entregaService;

    @Mock
    private EntregaRepository entregaRepository;

    private Entrega entregaExistente;

    @BeforeEach
    public void setUp() {
        entregaExistente = new Entrega(1L, 5, LocalDate.now().plusDays(7), "Maria Silva", "123.456.789-00",
                new Endereco("20000-000", "RJ", "Rio de Janeiro", "Centro", "Rua das Laranjeiras", "456", "Apt 301"));

        lenient().when(entregaRepository.findById(1L)).thenReturn(Optional.of(entregaExistente));
    }

    @Test
    @DisplayName("Deve cadastrar uma nova entrega")
    public void deveCadastrarEntrega() {

        EntregaDto entregaDto = new EntregaDto();
        entregaDto.setQuantidadePacotes(3);
        entregaDto.setDataLimiteEntrega(LocalDate.now().plusDays(5));
        entregaDto.setNomeCliente("Ana Souza");
        entregaDto.setCpfCliente("987.654.321-00");
        entregaDto.setEnderecoDestino(
                new Endereco("12345-678", "SP", "São Paulo", "Bela Vista", "Rua Augusta", "100", null));

        when(entregaRepository.save(any(Entrega.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Entrega novaEntrega = entregaService.cadastrarEntrega(entregaDto);

        assertNotNull(novaEntrega);
        assertEquals("Ana Souza", novaEntrega.getNomeCliente());
        assertEquals("987.654.321-00", novaEntrega.getCpfCliente());
        verify(entregaRepository).save(any(Entrega.class));
    }

    @Test
    @DisplayName("Deve consultar uma entrega existente")
    public void deveConsultarEntrega() {

        Entrega entrega = entregaService.consultarEntrega(1L);

        assertNotNull(entrega);
        assertEquals("Maria Silva", entrega.getNomeCliente());
        verify(entregaRepository).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao consultar entrega inexistente")
    public void deveLancarExcecaoAoConsultarEntregaInexistente() {

        when(entregaRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(EntregaNotFoundException.class, () -> entregaService.consultarEntrega(2L));
    }

    @Test
    @DisplayName("Deve atualizar uma entrega existente")
    public void deveAtualizarEntrega() {

        EntregaDto entregaDto = new EntregaDto();
        entregaDto.setNomeCliente("João Santos");
        entregaDto.setQuantidadePacotes(10);
        entregaDto.setDataLimiteEntrega(LocalDate.now().plusDays(10));
        entregaDto.setEnderecoDestino(
                new Endereco("99999-999", "SP", "São Paulo", "Centro", "Av Paulista", "1010", null));

        when(entregaRepository.save(any(Entrega.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Entrega entregaAtualizada = entregaService.atualizarEntrega(1L, entregaDto);

        assertNotNull(entregaAtualizada);
        assertEquals("João Santos", entregaAtualizada.getNomeCliente());
        assertEquals(10, entregaAtualizada.getQuantidadePacotes());
        verify(entregaRepository).save(any(Entrega.class));
    }

    @Test
    @DisplayName("Deve deletar uma entrega existente")
    public void deveDeletarEntrega() {

        when(entregaRepository.existsById(1L)).thenReturn(true);

        entregaService.deletarEntrega(1L);

        verify(entregaRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar entrega inexistente")
    public void deveLancarExcecaoAoDeletarEntregaInexistente() {

        when(entregaRepository.existsById(2L)).thenReturn(false);

        assertThrows(EntregaNotFoundException.class, () -> entregaService.deletarEntrega(2L));
    }
}