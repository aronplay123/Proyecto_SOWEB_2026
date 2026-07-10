package com.techacademy.backend_api;

import com.techacademy.backend_api.entity.Usuario;
import com.techacademy.backend_api.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApiApplication.class, args);
    }

    // Generador automático: Sincroniza al administrador usando el BCrypt nativo de Java
    @Bean
    public CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Si ya existe un intento previo de 'admin', lo limpiamos para actualizar el hash
            usuarioRepository.findByUsername("admin").ifPresent(usuarioRepository::delete);

            Usuario admin = new Usuario();
            admin.setUsername("admin");
            // Java encripta 'password123' usando exactamente el mismo Bean que usa para validar
            admin.setPassword(passwordEncoder.encode("password123")); 
            admin.setRol("ADMIN");

            usuarioRepository.save(admin);
            System.out.println("\n\n👉 ¡Usuario 'admin' creado y sincronizado con éxito en MySQL usando BCrypt nativo! 👈\n\n");
        };
    }
}