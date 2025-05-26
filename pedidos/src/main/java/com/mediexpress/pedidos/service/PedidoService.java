package com.mediexpress.pedidos.service;

import com.mediexpress.pedidos.dto.EntregaDto;
import com.mediexpress.pedidos.model.Pedido;
import com.mediexpress.pedidos.repository.PedidoRepository;
import com.mediexpress.pedidos.webclient.InventarioClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PedidoService {

    
    @Value("${logistica-service.url}")
    private String logisticaServiceUrl;

    private final PedidoRepository repository;
    private final InventarioClient inventarioClient;
    private final WebClient webClient;

    public PedidoService(PedidoRepository repository, InventarioClient inventarioClient, WebClient webClient) {
        this.repository = repository;
        this.inventarioClient = inventarioClient;
        this.webClient = webClient;
    }

    public List<Pedido> listar() {
        return repository.findAll();
    }

    public Optional<Pedido> buscar(Long id) {
        return repository.findById(id);
    }

    public Pedido guardar(Pedido pedido) {
        try {
            Map<String, Object> producto = inventarioClient.getProductoById(pedido.getProductoId());
            System.out.println("Producto encontrado: " + producto.get("nombre"));
        } catch (Exception e) {
            throw new RuntimeException("No se pudo validar el producto. Error: " + e.getMessage());
        }

        Pedido nuevo = repository.save(pedido);

        notificarLogistica(nuevo); // 🔗 Aquí se llama a logística

        return nuevo;
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private void notificarLogistica(Pedido pedido) {
        EntregaDto orden = new EntregaDto();
        orden.setPedidoId(pedido.getId());
        orden.setDireccionEntrega(pedido.getDireccionEntrega());
        orden.setFechaEntrega(LocalDate.now().plusDays(2)); // Puedes ajustar esta fecha

        webClient.post()
            .uri("http://localhost:8084/api/logistica") // Asegúrate de que esta URL es correcta
            .bodyValue(orden)
            .retrieve()
            .bodyToMono(Void.class)
            .onErrorResume(error -> {
                System.err.println("Error al notificar a logística: " + error.getMessage());
                return Mono.empty();
            })
            .subscribe();
    }
}
