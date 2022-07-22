package com.alkemy.challenge.controlador;

import com.alkemy.challenge.dto.*;
import com.alkemy.challenge.dto.proyeccion.IProyeccionPersonajeBusqueda;
import com.alkemy.challenge.mapper.PersonajeMapper;
import com.alkemy.challenge.servicio.PersonajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("characters")
public class PersonajeControlador {

    @Autowired
    private PersonajeServicio personajeServicio;

    @Autowired
    private PersonajeMapper personajeMapper;

    @PostMapping
    public ResponseEntity<PersonajeResponse> crearPersonaje(@Valid @RequestPart("personaje") PersonajeRequest personaje,
                                                            @RequestPart("imagen")MultipartFile imagen){
        return new ResponseEntity<>(this.personajeServicio.crearPersonaje(personaje,imagen), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonajeDTO> editarPersonaje(@PathVariable("id") Long id, @Valid @RequestPart("personaje")PersonajeEditadoRequest personaje,
                                                        @RequestPart("imagen") MultipartFile imagen){
        return new ResponseEntity<>(this.personajeServicio.editarPersonaje(id,personaje,imagen), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void eliminarPersonaje(@PathVariable("id") Long id){
        this.personajeServicio.eliminarPersonaje(id);
    }

    @GetMapping("/detalle")
    public ResponseEntity<List<PersonajeDTO>> obtenerDetallePersonajes(){
        return new ResponseEntity<>(this.personajeServicio.obtenerDetallePersonajes(),HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<PersonajeDTO> obtenerDetallePersonaje(@PathVariable(name = "id") Long idPersonaje){
        return new ResponseEntity<>(this.personajeServicio.obtenerDetallePersonaje(idPersonaje), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PersonajeBusquedaDTO>> obtenerPersonajes(){
        return new ResponseEntity<>(this.personajeServicio.obtenerPersonajes(), HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<PersonajeBusquedaDTO>> buscarPorNombre(@RequestParam(name = "name") String nombre){
        return new ResponseEntity<>(this.personajeServicio.buscarPorNombre(nombre), HttpStatus.OK);
    }

    @GetMapping(params = "age")
    public ResponseEntity<List<IProyeccionPersonajeBusqueda>> buscarPorEdad(@RequestParam(name = "age") int edad){
        return new ResponseEntity<>(this.personajeServicio.buscarPorEdad(edad), HttpStatus.OK);
    }

    @GetMapping(params = "movies")
    public ResponseEntity<List<IProyeccionPersonajeBusqueda>> buscarPorContenido(@RequestParam(name = "movies") long movies){
        return new ResponseEntity<>(this.personajeServicio.buscarPorContenido(movies), HttpStatus.OK);
    }
}
