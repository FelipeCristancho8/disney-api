package com.alkemy.challenge.controlador;

import com.alkemy.challenge.dto.*;
import com.alkemy.challenge.dto.proyeccion.ProyeccionContenidosBusqueda;
import com.alkemy.challenge.servicio.ContenidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class ContenidoControlador {

    @Autowired
    private ContenidoServicio contenidoServicio;

    @PostMapping
    public ResponseEntity<ContenidoResponse> crearContenido(@Valid @RequestPart("contenido")ContenidoRequest contenido, @RequestPart("imagen") MultipartFile imagen){
        return new ResponseEntity<>(this.contenidoServicio.crearContenido(contenido,imagen), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ContenidoDTO> editarContenido(@PathVariable("id") Long id,@Valid @RequestPart("contenido")ContenidoEditadoRequest contenido, @RequestPart("imagen") MultipartFile imagen){
        return new ResponseEntity<>(this.contenidoServicio.editarContenido(id,contenido,imagen), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void eliminarContenido(@PathVariable("id") Long id){
        this.contenidoServicio.eliminarContenidos(id);
    }

    @GetMapping("/detalle")
    public ResponseEntity<List<ContenidoDTO>>  listarContenidos(){
        return new ResponseEntity<>(this.contenidoServicio.listarContenidos(), HttpStatus.OK);
    }

    @GetMapping(params = "title")
    public ResponseEntity<List<ContenidoBusquedaDTO>> buscarPorTitulo(@RequestParam(name = "title") String titulo){
        return new ResponseEntity<>(this.contenidoServicio.buscarPorTitulo(titulo), HttpStatus.OK);
    }

    @GetMapping(params = "genre")
    public ResponseEntity<List<ProyeccionContenidosBusqueda>> buscarPorGenero(@RequestParam(name = "genre") Long idGenero){
        return new ResponseEntity<>(this.contenidoServicio.buscarPorGenero(idGenero), HttpStatus.OK);
    }

    @GetMapping(params = "order")
    public ResponseEntity<List<ProyeccionContenidosBusqueda>> buscarPorOrdenacion(@RequestParam(name = "order") String tipoOrdenacion){
        return new ResponseEntity<>(this.contenidoServicio.buscarPorOrdenacion(tipoOrdenacion), HttpStatus.OK);
    }
}
