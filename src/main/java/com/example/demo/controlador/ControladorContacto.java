package com.example.demo.controlador;

import com.example.demo.modelo.Contacto;
import com.example.demo.repositorio.RepositorioContacto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/contactos")
public class ControladorContacto {

    private final RepositorioContacto repositorioContacto;

    public ControladorContacto(RepositorioContacto repositorioContacto) {
        this.repositorioContacto = repositorioContacto;
    }

    @GetMapping
    public List<Contacto> listarContactos() {
        return repositorioContacto.findAll();
    }

    @PostMapping
    public Contacto crearContacto(@RequestBody Contacto contacto) {
        return repositorioContacto.save(contacto);
    }

    @PutMapping("/{id}")
    public Contacto actualizarContacto(@PathVariable Long id, @RequestBody Contacto contacto) {
        return repositorioContacto.findById(id)
                .map(c -> {
                    c.setNombre(contacto.getNombre());
                    c.setTelefono(contacto.getTelefono());
                    c.setEmail(contacto.getEmail());
                    return repositorioContacto.save(c);
                }).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void eliminarContacto(@PathVariable Long id) {
        repositorioContacto.deleteById(id);
    }

    @GetMapping("/buscar")
    public List<Contacto> buscarContactos(@RequestParam String nombre, @RequestParam String telefono) {
        return repositorioContacto.findByNombreContainingOrTelefonoContaining(nombre, telefono);
    }
}
