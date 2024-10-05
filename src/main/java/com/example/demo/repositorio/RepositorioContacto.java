package com.example.demo.repositorio;

import com.example.demo.modelo.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RepositorioContacto extends JpaRepository<Contacto, Long> {
    List<Contacto> findByNombreContainingOrTelefonoContaining(String nombre, String telefono);
}

