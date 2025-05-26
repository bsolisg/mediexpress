package com.mediexpress.configuracion.service;

import com.mediexpress.configuracion.model.Parametro;
import com.mediexpress.configuracion.repository.ParametroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParametroService {

    private final ParametroRepository repository;

    public ParametroService(ParametroRepository repository) {
        this.repository = repository;
    }

    public List<Parametro> listar() {
        return repository.findAll();
    }

    public Optional<Parametro> buscarPorClave(String clave) {
        return repository.findByClave(clave);
    }

    public Parametro guardar(Parametro parametro) {
        return repository.save(parametro);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Optional<Parametro> buscar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscar'");
    }
}
