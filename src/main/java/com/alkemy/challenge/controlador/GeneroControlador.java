package com.alkemy.challenge.controlador;

import com.alkemy.challenge.dto.GeneroRequest;
import com.alkemy.challenge.entidad.Genero;
import com.alkemy.challenge.servicio.GeneroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("generos")
public class GeneroControlador {

    @Autowired
    private GeneroServicio generoServicio;

    @GetMapping
    public ResponseEntity<List<Genero>> listarGeneros(){
        return new ResponseEntity<>(this.generoServicio.listarGeneros(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Genero> crearGenero(@RequestPart("genero") GeneroRequest genero,@RequestPart("imagen") MultipartFile imagen){
        return new ResponseEntity<>(this.generoServicio.crearGenero(genero,imagen), HttpStatus.OK);
    }
}
