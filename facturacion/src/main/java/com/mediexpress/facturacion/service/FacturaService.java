package com.mediexpress.facturacion.service;

import com.mediexpress.facturacion.client.ClienteClient;
import com.mediexpress.facturacion.model.Factura;
import com.mediexpress.facturacion.repository.FacturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FacturaService {

    private final FacturaRepository repository;
    private final ClienteClient clienteClient;

    public FacturaService(FacturaRepository repository, ClienteClient clienteClient) {
        this.repository = repository;
        this.clienteClient = clienteClient;
    }

    public List<Factura> listar() {
        return repository.findAll();
    }

    public Optional<Factura> buscar(Long id) {
        return repository.findById(id);
    }

    public Factura guardar(Factura factura) {
        try {
            Map<String, Object> cliente = clienteClient.getClienteById(factura.getClienteId());
            System.out.println("Cliente encontrado: " + cliente.get("nombre"));
        } catch (Exception e) {
            throw new RuntimeException("No se pudo validar el cliente. Error: " + e.getMessage());
        }
        return repository.save(factura);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
