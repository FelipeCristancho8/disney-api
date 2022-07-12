package com.alkemy.challenge.servicio;

import com.alkemy.challenge.dto.GeneroRequest;
import com.alkemy.challenge.entidad.Genero;
import com.alkemy.challenge.mapper.GeneroMapper;
import com.alkemy.challenge.repositorio.GeneroRepositorio;
import com.alkemy.challenge.utilidades.SubidaArchivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneroServicio {

    @Autowired
    GeneroRepositorio generoRepositorio;

    @Autowired
    GeneroMapper generoMapper;

    public List<Genero> listarGeneros(){
        return this.generoRepositorio.findAll();
    }

    public Genero crearGenero(GeneroRequest genero, MultipartFile imagen){
        String rutaImagen = "";
        Genero generoEntidad = this.generoMapper.generoRequestAGenero(genero);
        if(!imagen.isEmpty()){
            rutaImagen = SubidaArchivos.subirImagen(imagen);
            generoEntidad.setImagen(rutaImagen);
        }
        return this.generoRepositorio.save(generoEntidad);
    }

    public List<Genero> obtenerGeneros(List<Long> generos){
        if(verificarSiExistenGeneros(generos)){
            return generos.stream().map(idGenero -> this.generoRepositorio.findById(idGenero).orElse(null))
                    .collect(Collectors.toList());
        }
        //TODO enviar excepcion
        return null;
    }

    private boolean verificarSiExistenGeneros(List<Long> idGeneros){
        return idGeneros.stream().allMatch(id -> this.generoRepositorio.existsById(id));
    }
}
