package com.mediexpress.pedidos.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class InventarioClient {

    private final WebClient webClient;

    public InventarioClient(@Value("${inventario-service.url}") String inventarioServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(inventarioServiceUrl)
                .build();
    }

    public Map<String, Object> getProductoById(Long id) {
        return this.webClient.get()
            .uri("/{id}", id)
            .retrieve()
            .onStatus(
                status -> status.is4xxClientError(),
                response -> response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new RuntimeException("Producto no encontrado (ID: " + toString() + ")")))
            )
            .bodyToMono(Map.class)
            .block(); // Espera la respuesta de manera síncrona
    }
}
