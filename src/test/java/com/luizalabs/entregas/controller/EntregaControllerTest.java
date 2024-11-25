package com.luizalabs.entregas.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.luizalabs.entregas.dto.EntregaDto;
import com.luizalabs.entregas.exception.EntregaNotFoundException;
import com.luizalabs.entregas.model.Endereco;
import com.luizalabs.entregas.model.Entrega;
import com.luizalabs.entregas.service.EntregaService;

@ExtendWith(MockitoExtension.class)
public class EntregaControllerTest {

    @InjectMocks
    private EntregaController entregaController;

    @Mock
    private EntregaService entregaService;

    private Entrega entregaExistente;

    @BeforeEach
    public void setUp() {
        entregaExistente = new Entrega(1L, 5, LocalDate.now().plusDays(7), "Maria Silva", "123.456.789-00",
                new Endereco("20000-000", "RJ", "Rio de Janeiro", "Centro", "Rua das Laranjeiras", "456", "Apt 301"));
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

        when(entregaService.cadastrarEntrega(any(EntregaDto.class))).thenReturn(entregaExistente);

        ResponseEntity<Entrega> response = entregaController.cadastrarEntrega(entregaDto);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Maria Silva", response.getBody().getNomeCliente());
        verify(entregaService).cadastrarEntrega(any(EntregaDto.class));
    }

    @Test
    @DisplayName("Deve consultar uma entrega existente")
    public void deveConsultarEntrega() {
        
        when(entregaService.consultarEntrega(1L)).thenReturn(entregaExistente);

        ResponseEntity<Entrega> response = entregaController.consultarEntrega(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Maria Silva", response.getBody().getNomeCliente());
        verify(entregaService).consultarEntrega(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao consultar entrega inexistente")
    public void deveLancarExcecaoAoConsultarEntregaInexistente() {
        
        when(entregaService.consultarEntrega(2L)).thenThrow(new EntregaNotFoundException("Entrega não encontrada"));

        EntregaNotFoundException exception = assertThrows(EntregaNotFoundException.class,
                () -> entregaController.consultarEntrega(2L));
        assertEquals("Entrega não encontrada", exception.getMessage());
        verify(entregaService).consultarEntrega(2L);
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

        when(entregaService.atualizarEntrega(1L, entregaDto)).thenReturn(entregaExistente);

        ResponseEntity<Entrega> response = entregaController.atualizarEntrega(1L, entregaDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Maria Silva", response.getBody().getNomeCliente());
        verify(entregaService).atualizarEntrega(eq(1L), any(EntregaDto.class));
    }

    @Test
    @DisplayName("Deve deletar uma entrega existente")
    public void deveDeletarEntrega() {
        
        ResponseEntity<Map<String, String>> response = entregaController.deletarEntrega(1L);

        
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Entrega com ID 1 foi deletada com sucesso.", response.getBody().get("message"));
        verify(entregaService).deletarEntrega(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar entrega inexistente")
    public void deveLancarExcecaoAoDeletarEntregaInexistente() {
        
        doThrow(new EntregaNotFoundException("Entrega não encontrada")).when(entregaService).deletarEntrega(2L);

        EntregaNotFoundException exception = assertThrows(EntregaNotFoundException.class,
                () -> entregaController.deletarEntrega(2L));
        assertEquals("Entrega não encontrada", exception.getMessage());
        verify(entregaService).deletarEntrega(2L);
    }
}
