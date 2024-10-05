package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Se aplica a todas las rutas que comiencen con "/api/"
                        .allowedOrigins("http://localhost:5173") // Permitir peticiones del frontend (Vite)
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir estos métodos HTTP
                        .allowedHeaders("*") // Permitir todos los headers
                        .allowCredentials(true); // Permitir envío de cookies o credenciales
            }
        };
    }
}
