package com.mediexpress.usuarios.controller;

import com.mediexpress.usuarios.model.Usuario;
import com.mediexpress.usuarios.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // GET - listar todos
    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    // GET - obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtener(@PathVariable Long id) {
        return service.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - crear
    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return service.guardar(usuario);
    }

    // PUT - reemplazar completamente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> reemplazar(@PathVariable Long id, @RequestBody Usuario nuevo) {
        return service.buscar(id)
                .map(existente -> {
                    existente.setNombre(nuevo.getNombre());
                    existente.setEmail(nuevo.getEmail());
                    existente.setPassword(nuevo.getPassword());
                    existente.setRol(nuevo.getRol());
                    return ResponseEntity.ok(service.guardar(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // PATCH - actualizar parcialmente
    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Map<String, Object> cambios) {
        return service.buscar(id)
                .map(usuario -> {
                    cambios.forEach((clave, valor) -> {
                        switch (clave) {
                            case "nombre" -> usuario.setNombre((String) valor);
                            case "email" -> usuario.setEmail((String) valor);
                            case "password" -> usuario.setPassword((String) valor);
                            case "rol" -> usuario.setRol((String) valor);
                        }
                    });
                    return ResponseEntity.ok(service.guardar(usuario));
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
