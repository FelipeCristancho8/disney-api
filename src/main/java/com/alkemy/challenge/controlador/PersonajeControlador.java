package com.alkemy.challenge.controlador;

import com.alkemy.challenge.dto.PersonajeDTO;
import com.alkemy.challenge.dto.PersonajeRequest;
import com.alkemy.challenge.dto.PersonajeResponse;
import com.alkemy.challenge.entidad.Personaje;
import com.alkemy.challenge.mapper.PersonajeMapper;
import com.alkemy.challenge.servicio.PersonajeServicio;
import com.alkemy.challenge.utilidades.SubidaArchivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("characters")
public class PersonajeControlador {

    @Autowired
    private PersonajeServicio personajeServicio;

    @Autowired
    private PersonajeMapper personajeMapper;


    @GetMapping
    public List<PersonajeDTO> listarPersonajes(){
        return this.personajeServicio.listarPersonajes();
    }

    @PostMapping
    public PersonajeResponse crearPersonaje(@Valid @RequestPart("personaje") PersonajeRequest personaje, @RequestPart("imagen")MultipartFile imagen){
        return this.personajeServicio.crearPersonaje(personaje,imagen);
    }

    @GetMapping("/buscar")
    public List<PersonajeDTO> buscarPorNombre(@RequestParam(name = "name") String nombre){
        return this.personajeServicio.buscarPorNombre(nombre);
    }

    @GetMapping("/fecha")
    public List<PersonajeDTO> buscarPorEdad(@RequestParam(name = "age") int edad){
        return this.personajeServicio.buscarPorEdad(edad);
    }
}
