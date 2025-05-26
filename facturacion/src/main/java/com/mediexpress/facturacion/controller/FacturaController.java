package com.mediexpress.facturacion.controller;

import com.mediexpress.facturacion.model.Factura;
import com.mediexpress.facturacion.service.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final FacturaService service;

    public FacturaController(FacturaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Factura> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtener(@PathVariable Long id) {
        return service.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Factura crear(@RequestBody Factura factura) {
        return service.guardar(factura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> reemplazar(@PathVariable Long id, @RequestBody Factura nuevo) {
        return service.buscar(id)
                .map(existente -> {
                    existente.setMonto(nuevo.getMonto());
                    existente.setFecha(nuevo.getFecha());
                    existente.setDescripcion(nuevo.getDescripcion());
                    existente.setClienteId(nuevo.getClienteId());
                    return ResponseEntity.ok(service.guardar(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Factura> actualizar(@PathVariable Long id, @RequestBody Map<String, Object> cambios) {
        return service.buscar(id)
                .map(factura -> {
                    cambios.forEach((clave, valor) -> {
                        switch (clave) {
                            case "monto" -> factura.setMonto(Double.valueOf(valor.toString()));
                            case "fecha" -> factura.setFecha(LocalDate.parse(valor.toString()));
                            case "descripcion" -> factura.setDescripcion(valor.toString());
                            case "clienteId" -> factura.setClienteId(Long.valueOf(valor.toString()));
                        }
                    });
                    return ResponseEntity.ok(service.guardar(factura));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
