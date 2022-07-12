package com.alkemy.challenge.controlador;

import com.alkemy.challenge.dto.ContenidoDTO;
import com.alkemy.challenge.entidad.Contenido;
import com.alkemy.challenge.servicio.ContenidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
