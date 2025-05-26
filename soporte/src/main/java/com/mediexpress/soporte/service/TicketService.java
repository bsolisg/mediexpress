package com.mediexpress.soporte.service;

import com.mediexpress.soporte.model.Ticket;
import com.mediexpress.soporte.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public List<Ticket> listar() {
        return repository.findAll();
    }

    public Optional<Ticket> buscar(Long id) {
        return repository.findById(id);
    }

    public Ticket guardar(Ticket ticket) {
        return repository.save(ticket);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
