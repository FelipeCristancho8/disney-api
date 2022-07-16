package com.alkemy.challenge.servicio;

import com.alkemy.challenge.dto.GeneroRequest;
import com.alkemy.challenge.entidad.Genero;
import com.alkemy.challenge.mapper.GeneroMapper;
import com.alkemy.challenge.repositorio.GeneroRepositorio;
import com.alkemy.challenge.utilidades.SubidaArchivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
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
        if(!validarExistenciaGenero(genero.getNombre())){
            String rutaImagen = "";
            Genero generoEntidad = this.generoMapper.generoRequestAGenero(genero);
            if (!imagen.isEmpty()) {
                rutaImagen = SubidaArchivos.subirImagen(imagen);
                generoEntidad.setImagen(rutaImagen);
            }
            return this.generoRepositorio.save(generoEntidad);
        }
        throw new DuplicateKeyException("El genero ya existe");
    }

    public List<Genero> obtenerGeneros(List<Long> generos){
        return generos.stream().map(idGenero -> this.generoRepositorio.findById(idGenero)
            .orElseThrow(() -> new NoSuchElementException("Genero no encontrado")))
            .collect(Collectors.toList());
    }

    public boolean validarExistenciaGenero(String nombre){
        return this.generoRepositorio.existsByNombre(nombre);
    }
}
