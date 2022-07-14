package com.alkemy.challenge.controlador;

import com.alkemy.challenge.dto.ContenidoDTO;
import com.alkemy.challenge.dto.ContenidoRequest;
import com.alkemy.challenge.dto.ContenidoResponse;
import com.alkemy.challenge.entidad.Contenido;
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

    @GetMapping
    public List<ContenidoDTO> listarContenidos(){
        return this.contenidoServicio.listarContenidos();
    }

    @PostMapping
    public ResponseEntity<ContenidoResponse> crearContenido(@Valid @RequestPart("contenido")ContenidoRequest contenido, @RequestPart("imagen") MultipartFile imagen){
        return new ResponseEntity<>(this.contenidoServicio.crearContenido(contenido,imagen), HttpStatus.CREATED);
    }
}
