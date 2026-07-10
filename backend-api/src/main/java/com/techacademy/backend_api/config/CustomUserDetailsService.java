package com.techacademy.backend_api.config;

import com.techacademy.backend_api.entity.Usuario;
import com.techacademy.backend_api.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado en MySQL: " + username));

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword()) // Este es el hash BCrypt que está en MySQL
                .roles(usuario.getRol())
                .build();
    }
}