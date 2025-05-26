package com.mediexpress.logistica.service;

import com.mediexpress.logistica.model.Entrega;
import com.mediexpress.logistica.repository.EntregaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {

    private final EntregaRepository repository;

    public EntregaService(EntregaRepository repository) {
        this.repository = repository;
    }

    public List<Entrega> listar() {
        return repository.findAll();
    }

    public Optional<Entrega> buscar(Long id) {
        return repository.findById(id);
    }

    public Entrega guardar(Entrega entrega) {
        return repository.save(entrega);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
