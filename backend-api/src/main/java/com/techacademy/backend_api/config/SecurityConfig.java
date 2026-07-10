package com.techacademy.backend_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Activar la configuración CORS global a nivel de filtro de seguridad
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) 
            .csrf(csrf -> csrf.disable()) // Deshabilitado para APIs REST
            .authorizeHttpRequests(auth -> auth
                // 2. CRÍTICO: Permitir todas las peticiones OPTIONS (Preflight) que hace el navegador
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // Dejamos públicos los métodos GET (Lectura del catálogo)
                .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                // Toda mutación de datos (POST, PUT, DELETE) requiere autenticación
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // Usa Autenticación Básica

        return http.build();
    }

    // 3. Definición explícita de reglas CORS para que Spring Security no bloquee a Angular
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Define BCrypt como el algoritmo de encriptación
    }
}