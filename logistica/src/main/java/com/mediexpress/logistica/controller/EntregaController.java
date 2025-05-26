package com.mediexpress.logistica.controller;

import com.mediexpress.logistica.dto.EntregaDto;
import com.mediexpress.logistica.model.Entrega;
import com.mediexpress.logistica.service.EntregaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/entregas")
public class EntregaController {

    private final EntregaService service;

    public EntregaController(EntregaService service) {
        this.service = service;
    }

    // GET - listar todas
    @GetMapping
    public List<Entrega> listar() {
        return service.listar();
    }

    // GET - obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Entrega> obtener(@PathVariable Long id) {
        return service.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - crear
    @PostMapping
    public Entrega crear(@RequestBody EntregaDto dto) {
    Entrega entrega = new Entrega();
    entrega.setPedidoId(dto.getPedidoId());
    entrega.setDireccionEntrega(dto.getDireccionEntrega());
    entrega.setEstadoEntrega(dto.getEstadoEntrega());
    entrega.setFechaEntrega(dto.getFechaEntrega());

    return service.guardar(entrega);
    }


    // PUT - reemplazar completamente
    @PutMapping("/{id}")
    public ResponseEntity<Entrega> reemplazar(@PathVariable Long id, @RequestBody Entrega nueva) {
        return service.buscar(id)
                .map(existente -> {
                    existente.setPedidoId(nueva.getPedidoId());
                    existente.setDireccionEntrega(nueva.getDireccionEntrega());
                    existente.setEstadoEntrega(nueva.getEstadoEntrega());
                    existente.setDireccionEntrega(nueva.getDireccionEntrega());
                    return ResponseEntity.ok(service.guardar(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // PATCH - actualizar parcialmente
    @PatchMapping("/{id}")
    public ResponseEntity<Entrega> actualizar(@PathVariable Long id, @RequestBody Map<String, Object> cambios) {
        return service.buscar(id)
                .map(entrega -> {
                    cambios.forEach((clave, valor) -> {
                        switch (clave) {
                            case "pedidoId" -> entrega.setPedidoId(Long.parseLong(valor.toString()));
                            case "direccionEntrega" -> entrega.setDireccionEntrega((String) valor);
                            case "estadoEntrega" -> entrega.setEstadoEntrega((String) valor);
                            case "fechaEntrega" -> entrega.setFechaEntrega(java.time.LocalDate.parse(valor.toString()));
                        }
                    });
                    return ResponseEntity.ok(service.guardar(entrega));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE - eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
