package com.alkemy.challenge.servicio;

import com.alkemy.challenge.dto.ContenidoDTO;
import com.alkemy.challenge.dto.ContenidoRequest;
import com.alkemy.challenge.entidad.Contenido;
import com.alkemy.challenge.mapper.ContenidoMapper;
import com.alkemy.challenge.repositorio.ContenidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContenidoServicio {

    @Autowired
    private ContenidoRepositorio contenidoRepositorio;

    @Autowired
    private ContenidoMapper contenidoMapper;

    public List<ContenidoDTO> listarContenidos(){
        return this.contenidoMapper.ContenidosAContenidosDTO(this.contenidoRepositorio.findAll());
    }

    public void crearContenido(ContenidoRequest contenido, MultipartFile imagen){
        //TODO
    }
    public List<Contenido> obtenerContenidos(List<Long> contenidos){
        if (verficarSiExistenContenidos(contenidos)){
            return contenidos.stream().map(idContenido -> this.contenidoRepositorio.findById(idContenido).orElse(null))
                .collect(Collectors.toList());
        }
        //TODO enviar excepcion
        return null;
    }
    private boolean verficarSiExistenContenidos(List<Long> idContenidos){
        return idContenidos.stream().allMatch(id ->this.contenidoRepositorio.existsById(id));
    }

}
