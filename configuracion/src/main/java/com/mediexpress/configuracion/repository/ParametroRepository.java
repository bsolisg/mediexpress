package com.mediexpress.configuracion.repository;

import com.mediexpress.configuracion.model.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParametroRepository extends JpaRepository<Parametro, Long> {
    Optional<Parametro> findByClave(String clave);
}
