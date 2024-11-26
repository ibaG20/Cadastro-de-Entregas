package com.luizalabs.entregas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.luizalabs.entregas.dto.EntregaDto;
import com.luizalabs.entregas.model.Entrega;
import com.luizalabs.entregas.service.EntregaService;

import jakarta.validation.Valid;

import java.util.Map;

@RestController
@RequestMapping("/api/entregas")
@Validated
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @PostMapping("/")
    public ResponseEntity<Entrega> cadastrarEntrega(@Valid @RequestBody EntregaDto entregaDto) {
        Entrega novaEntrega = entregaService.cadastrarEntrega(entregaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEntrega);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> consultarEntrega(@PathVariable Long id) {
        Entrega entrega = entregaService.consultarEntrega(id);
        return ResponseEntity.ok(entrega);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrega> atualizarEntrega(@PathVariable Long id, @Valid @RequestBody EntregaDto entregaDto) {
        Entrega entregaAtualizada = entregaService.atualizarEntrega(id, entregaDto);
        return ResponseEntity.ok(entregaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarEntrega(@PathVariable Long id) {
        entregaService.deletarEntrega(id);
        return ResponseEntity.ok(Map.of("message", "Entrega com ID " + id + " foi deletada com sucesso."));
    }
}