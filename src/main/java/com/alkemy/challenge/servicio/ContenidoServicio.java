package com.alkemy.challenge.servicio;

import com.alkemy.challenge.dto.ContenidoDTO;
import com.alkemy.challenge.dto.ContenidoRequest;
import com.alkemy.challenge.dto.ContenidoResponse;
import com.alkemy.challenge.entidad.Contenido;
import com.alkemy.challenge.entidad.Genero;
import com.alkemy.challenge.entidad.Personaje;
import com.alkemy.challenge.mapper.ContenidoMapper;
import com.alkemy.challenge.repositorio.ContenidoRepositorio;
import com.alkemy.challenge.utilidades.SubidaArchivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContenidoServicio {

    @Autowired
    private ContenidoRepositorio contenidoRepositorio;

    @Lazy
    @Autowired
    private PersonajeServicio personajeServicio;

    @Autowired
    private ContenidoMapper contenidoMapper;

    @Autowired
    private GeneroServicio generoServicio;

    public List<ContenidoDTO> listarContenidos() {
        return this.contenidoMapper.contenidosAContenidosDTO(this.contenidoRepositorio.findAll());
    }


    public ContenidoResponse crearContenido(ContenidoRequest contenido, MultipartFile imagen) {
        //TODO hacer la conversion a la salida (return) que se pide en el reto
        String rutaImagen = "";
        List<Personaje> personajes = this.personajeServicio.obtenerPersonajes(new ArrayList<>(contenido.getPersonajesAsociados()));
        List<Genero> generos = this.generoServicio.obtenerGeneros(new ArrayList<>(contenido.getGenerosAsociados()));
        Contenido contenidoEntidad = this.contenidoMapper.contenidoRequestAContenido(contenido);
        if(!imagen.isEmpty()){
            rutaImagen = SubidaArchivos.subirImagen(imagen);
            contenidoEntidad.setImagen(rutaImagen);
        }
        if(generos != null){
            contenidoEntidad.agregarGeneros(generos);
        }
        Contenido contenidoAlmacenado = this.contenidoRepositorio.save(contenidoEntidad);
        if (personajes != null) {
            this.personajeServicio.agregarContenidoAPersonajes(contenidoAlmacenado,personajes);
            contenidoAlmacenado.agregarPersonajes(personajes);
        }
        return this.contenidoMapper.contenidoAContenidoResponse(contenidoAlmacenado);
        //return null;
    }

    public List<Contenido> obtenerContenidos(List<Long> contenidos) {
        if (verficarSiExistenContenidos(contenidos)) {
            return contenidos.stream().map(idContenido -> this.contenidoRepositorio.findById(idContenido).orElse(null))
                    .collect(Collectors.toList());
        }
        //TODO enviar excepcion
        return null;
    }

    private boolean verficarSiExistenContenidos(List<Long> idContenidos) {
        return idContenidos.stream().allMatch(id -> this.contenidoRepositorio.existsById(id));
    }

}
