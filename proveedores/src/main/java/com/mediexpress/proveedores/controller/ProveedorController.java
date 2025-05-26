package com.mediexpress.proveedores.controller;

import com.mediexpress.proveedores.model.Proveedor;
import com.mediexpress.proveedores.service.ProveedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorService service;

    public ProveedorController(ProveedorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Proveedor> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtener(@PathVariable Long id) {
        return service.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Proveedor crear(@RequestBody Proveedor proveedor) {
        return service.guardar(proveedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> reemplazar(@PathVariable Long id, @RequestBody Proveedor nuevo) {
        return service.buscar(id)
                .map(existente -> {
                    existente.setNombre(nuevo.getNombre());
                    existente.setCorreo(nuevo.getCorreo());
                    existente.setCorreo(nuevo.getCorreo());
                    existente.setRubro(nuevo.getRubro());
                    return ResponseEntity.ok(service.guardar(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
public ResponseEntity<Proveedor> actualizar(@PathVariable Long id, @RequestBody Map<String, Object> cambios) {
    return service.buscar(id)
            .map(proveedor -> {
                cambios.forEach((clave, valor) -> {
                    switch (clave) {
                        case "nombre" -> proveedor.setNombre((String) valor);
                        case "correo" -> proveedor.setCorreo((String) valor);
                        case "contacto" -> proveedor.setCorreo((String) valor);
                        case "rubro" -> proveedor.setRubro((String) valor);
                    }
                });
                return ResponseEntity.ok(service.guardar(proveedor));
            })
            .orElse(ResponseEntity.notFound().build());
    }
}