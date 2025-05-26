package com.mediexpress.soporte.controller;

import com.mediexpress.soporte.model.Ticket;
import com.mediexpress.soporte.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @GetMapping
    public List<Ticket> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> obtener(@PathVariable Long id) {
        return service.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ticket crear(@RequestBody Ticket ticket) {
        return service.guardar(ticket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> reemplazar(@PathVariable Long id, @RequestBody Ticket nuevo) {
    return service.buscar(id)
            .map(existente -> {
                existente.setCliente(nuevo.getCliente());
                existente.setDescripcion(nuevo.getDescripcion());
                existente.setEstado(nuevo.getEstado());
                existente.setFechaCreacion(nuevo.getFechaCreacion());
                return ResponseEntity.ok(service.guardar(existente));
            })
            .orElse(ResponseEntity.notFound().build());
}


    @PatchMapping("/{id}")
    public ResponseEntity<Ticket> actualizar(@PathVariable Long id, @RequestBody Map<String, Object> cambios) {
        return service.buscar(id)
                .map(ticket -> {
                    cambios.forEach((clave, valor) -> {
                        switch (clave) {
                            case "cliente" -> ticket.setCliente((String) valor);
                            case "descripcion" -> ticket.setDescripcion((String) valor);
                            case "estado" -> ticket.setEstado((String) valor);
                            case "fechaCreacion" -> ticket.setFechaCreacion(LocalDate.parse((String) valor));
                        }
                    });
                    return ResponseEntity.ok(service.guardar(ticket));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
