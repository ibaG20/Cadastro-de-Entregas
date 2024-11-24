package com.luizalabs.entregas.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.luizalabs.entregas.dto.EntregaDto;
import com.luizalabs.entregas.exception.EntregaNotFoundException;
import com.luizalabs.entregas.model.Entrega;
import com.luizalabs.entregas.repository.EntregaRepository;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;

    public EntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public Entrega consultarEntrega(Long id) {
        return this.entregaRepository.findById(id)
                .orElseThrow(() -> new EntregaNotFoundException("Entrega com ID " + id + " não encontrada."));
    }

    public Entrega cadastrarEntrega(EntregaDto entregaDto) {
        Entrega novaEntrega = new Entrega(entregaDto);
        return this.entregaRepository.save(novaEntrega);
    }

    public Entrega atualizarEntrega(Long id, EntregaDto entregaDto) {
        Entrega entregaExistente = this.consultarEntrega(id);
        BeanUtils.copyProperties(entregaDto, entregaExistente, "id");
        return this.entregaRepository.save(entregaExistente);
    }

    public void deletarEntrega(Long id) {
        if (!this.entregaRepository.existsById(id)) {
            throw new EntregaNotFoundException("Entrega com ID " + id + " não encontrada.");
        }
        this.entregaRepository.deleteById(id);
    }
}
