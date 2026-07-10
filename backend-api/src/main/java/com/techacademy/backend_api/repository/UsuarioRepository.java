package com.techacademy.backend_api.repository;

import com.techacademy.backend_api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Consulta JPA inteligente: Busca un usuario por su nombre exacto en MySQL
    Optional<Usuario> findByUsername(String username);
}