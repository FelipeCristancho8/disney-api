package com.alkemy.challenge.controlador;

import com.alkemy.challenge.dto.GeneroRequest;
import com.alkemy.challenge.entidad.Genero;
import com.alkemy.challenge.servicio.GeneroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("generos")
public class GeneroControlador {

    @Autowired
    private GeneroServicio generoServicio;

    @RequestMapping
    public List<Genero> listarGeneros(){
        return this.generoServicio.listarGeneros();
    }

    @PostMapping
    public Genero crearGenero(@RequestPart("genero") GeneroRequest genero,@RequestPart("imagen") MultipartFile imagen){
        return this.generoServicio.crearGenero(genero,imagen);
    }
}
