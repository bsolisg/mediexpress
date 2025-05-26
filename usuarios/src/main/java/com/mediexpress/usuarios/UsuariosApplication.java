package com.mediexpress.usuarios;

import com.mediexpress.usuarios.model.Usuario;
import com.mediexpress.usuarios.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UsuariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuariosApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UsuarioRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                Usuario user = new Usuario();
                user.setNombre("Usuario Demo");
                user.setEmail("demo@correo.cl");
                user.setPassword("1234");
                user.setRol("ADMIN");
                repo.save(user);
                System.out.println("✅ Usuario demo creado correctamente.");
            }
        };
    }
}
