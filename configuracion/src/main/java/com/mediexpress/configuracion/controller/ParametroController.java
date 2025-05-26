package com.mediexpress.configuracion.controller;

import com.mediexpress.configuracion.model.Parametro;
import com.mediexpress.configuracion.service.ParametroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/config")
public class ParametroController {

    private final ParametroService service;

    public ParametroController(ParametroService service) {
        this.service = service;
    }

    @GetMapping
    public List<Parametro> listar() {
        return service.listar();
    }

    @GetMapping("/{clave}")
    public ResponseEntity<Parametro> buscar(@PathVariable String clave) {
        return service.buscarPorClave(clave)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Parametro crear(@RequestBody Parametro parametro) {
        return service.guardar(parametro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parametro> reemplazar(@PathVariable Long id, @RequestBody Parametro nuevo) {
    return service.buscar(id)
            .map(existente -> {
                existente.setClave(nuevo.getClass());  // ✅ cambio real aquí
                existente.setValor(nuevo.getValor());
                return ResponseEntity.ok(service.guardar(existente));
            })
            .orElse(ResponseEntity.notFound().build());
}




    @PatchMapping("/{id}")
    public ResponseEntity<Parametro> actualizar(@PathVariable Long id, @RequestBody Map<String, Object> cambios) {
        return service.buscar(id)
                .map(parametro -> {
                    cambios.forEach((clave, valor) -> {
                        switch (clave) {
                            case "clave" -> parametro.setClave((String) valor);
                            case "valor" -> parametro.setValor((String) valor);
                        }
                    });
                    return ResponseEntity.ok(service.guardar(parametro));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
