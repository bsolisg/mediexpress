package com.mediexpress.proveedores.service;

import com.mediexpress.proveedores.model.Proveedor;
import com.mediexpress.proveedores.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    private final ProveedorRepository repository;

    public ProveedorService(ProveedorRepository repository) {
        this.repository = repository;
    }

    public List<Proveedor> listar() {
        return repository.findAll();
    }

    public Optional<Proveedor> buscar(Long id) {
        return repository.findById(id);
    }

    public Proveedor guardar(Proveedor proveedor) {
        return repository.save(proveedor);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
