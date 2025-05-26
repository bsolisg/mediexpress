package com.mediexpress.pedidos.controller;

import com.mediexpress.pedidos.model.Pedido;
import com.mediexpress.pedidos.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtener(@PathVariable Long id) {
        return service.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {
        return service.guardar(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> reemplazar(@PathVariable Long id, @RequestBody Pedido nuevo) {
    return service.buscar(id)
            .map(existente -> {
                existente.setProducto(nuevo.getProducto());
                existente.setCantidad(nuevo.getCantidad());
                existente.setPrecioUnitario(nuevo.getPrecioUnitario());
                existente.setProductoId(nuevo.getProductoId());  // <--- CORREGIDO
                existente.setFecha(nuevo.getFecha());
                return ResponseEntity.ok(service.guardar(existente));
            })
            .orElse(ResponseEntity.notFound().build());
}


    @PatchMapping("/{id}")
public ResponseEntity<Pedido> actualizar(@PathVariable Long id, @RequestBody Map<String, Object> cambios) {
    return service.buscar(id)
            .map(pedido -> {
                cambios.forEach((clave, valor) -> {
                    switch (clave) {
                        case "producto" -> pedido.setProducto(valor.toString());
                        case "cantidad" -> pedido.setCantidad(Integer.valueOf(valor.toString()));
                        case "precioUnitario" -> pedido.setPrecioUnitario(Double.valueOf(valor.toString()));
                        case "productoId" -> pedido.setProductoId(Long.valueOf(valor.toString())); // <--- CORREGIDO
                        case "fecha" -> pedido.setFecha(LocalDate.parse(valor.toString()));
                    }
                });
                return ResponseEntity.ok(service.guardar(pedido));
            })
            .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
