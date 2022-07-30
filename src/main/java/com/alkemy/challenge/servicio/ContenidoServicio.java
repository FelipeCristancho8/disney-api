package com.alkemy.challenge.servicio;

import com.alkemy.challenge.dto.*;
import com.alkemy.challenge.dto.proyeccion.ProyeccionContenidosBusqueda;
import com.alkemy.challenge.entidad.Contenido;
import com.alkemy.challenge.entidad.Genero;
import com.alkemy.challenge.entidad.Personaje;
import com.alkemy.challenge.excepcion.ElementoDuplicadoExcepcion;
import com.alkemy.challenge.excepcion.ElementoNoEncontradoExcepcion;
import com.alkemy.challenge.mapper.ContenidoMapper;
import com.alkemy.challenge.repositorio.ContenidoRepositorio;
import com.alkemy.challenge.utilidades.SubidaArchivos;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContenidoServicio {

    private static final String CONTENIDO_NO_ENCONTRADO = "Contenido no encontrado";
    private static final String PERSONAJE_DUPLICADO = "El personaje ya existe en la pelicula";

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
        String rutaImagen = "";

        Contenido contenidoEntidad = this.contenidoMapper.contenidoRequestAContenido(contenido);
        if (!imagen.isEmpty()) {
            rutaImagen = SubidaArchivos.subirImagen(imagen);
            contenidoEntidad.setImagen(rutaImagen);
        }

        if(contenido.getGenerosAsociados() != null){
            List<Genero> generos = this.generoServicio.obtenerGeneros(new ArrayList<>(contenido.getGenerosAsociados()));
            contenidoEntidad.agregarGeneros(generos);
        }

        Contenido contenidoAlmacenado = this.contenidoRepositorio.save(contenidoEntidad);
        if(contenido.getPersonajesAsociados() != null){
            System.out.println("entro a personajes asociados");
            List<Personaje> personajes = this.personajeServicio.obtenerPersonajes(new ArrayList<>(contenido.getPersonajesAsociados()));
            this.personajeServicio.agregarContenidoAPersonajes(contenidoAlmacenado, personajes);
            contenidoAlmacenado.agregarPersonajes(personajes);
        }
        return this.contenidoMapper.contenidoAContenidoResponse(contenidoAlmacenado);
    }

    public ContenidoDTO editarContenido(Long idContenido, ContenidoEditadoRequest contenidoEditadoRequest, MultipartFile imagen){
        String rutaImagen = "";
        Contenido contenidoAEditar = this.contenidoRepositorio.findById(idContenido)
                .orElseThrow(() -> new ElementoNoEncontradoExcepcion(CONTENIDO_NO_ENCONTRADO));
        if (!imagen.isEmpty()) {
            rutaImagen = SubidaArchivos.subirImagen(imagen);
            contenidoAEditar.setImagen(rutaImagen);
        }
        this.mapearCamposValidos(contenidoEditadoRequest,contenidoAEditar);
        return this.contenidoMapper.contenidoAContenidoDTO(this.contenidoRepositorio.save(contenidoAEditar));
    }

    private void mapearCamposValidos(ContenidoEditadoRequest contenidoRequest, Contenido contenido){
        if(!StringUtils.isBlank(contenidoRequest.getTitulo())){
            contenido.setTitulo(contenidoRequest.getTitulo());
        }
        if(contenidoRequest.getFechaCreacion() != null){
            contenido.setFechaCreacion(contenidoRequest.getFechaCreacion());
        }
        if(contenidoRequest.getCalificacion() != null){
            contenido.setCalificacion(contenidoRequest.getCalificacion());
        }
    }

    public void eliminarContenidos(Long id){
        if(!this.contenidoRepositorio.existsById(id)){
            throw new ElementoNoEncontradoExcepcion(CONTENIDO_NO_ENCONTRADO);
        }
        this.contenidoRepositorio.deleteById(id);
    }


    public ContenidoDTO agregarPersonajeAContenido(Long idContenido, Long idPersonaje){
        Contenido contenido = this.contenidoRepositorio.findById(idContenido)
                .orElseThrow(() -> new ElementoNoEncontradoExcepcion(CONTENIDO_NO_ENCONTRADO));
        Personaje personaje = this.personajeServicio.obtenerPersonaje(idPersonaje);
        if(this.verificarExistenciaPersonajeEnContenido(personaje, contenido.getPersonajesAsociados())){
            throw new ElementoDuplicadoExcepcion(PERSONAJE_DUPLICADO);
        }
        this.personajeServicio.agregarPersonajeAContenido(contenido,personaje);
        contenido.agregarPersonaje(personaje);
        return this.contenidoMapper.contenidoAContenidoDTO(this.contenidoRepositorio.save(contenido));
    }

    public ContenidoDTO removerPersonajeDeContenido(Long idContenido, Long idPersonaje){
        Contenido contenido = this.contenidoRepositorio.findById(idContenido)
                .orElseThrow(() -> new ElementoNoEncontradoExcepcion(CONTENIDO_NO_ENCONTRADO));
        Personaje personaje = this.personajeServicio.obtenerPersonaje(idPersonaje);
        if(!this.verificarExistenciaPersonajeEnContenido(personaje, contenido.getPersonajesAsociados())){
            throw new ElementoNoEncontradoExcepcion(CONTENIDO_NO_ENCONTRADO);
        }
        this.personajeServicio.eliminarPersonajeDeContenido(contenido,personaje);
        contenido.eliminarPersonaje(personaje);
        return this.contenidoMapper.contenidoAContenidoDTO(this.contenidoRepositorio.save(contenido));
    }

    private boolean verificarExistenciaPersonajeEnContenido(Personaje personajeAEncontrar, Set<Personaje> personajes) {
        return personajes.stream().anyMatch(personaje -> personajeAEncontrar.equals(personaje));
    }

    public List<Contenido> obtenerContenidos(List<Long> contenidos) {
        return contenidos.stream().map(idContenido -> this.contenidoRepositorio.findById(idContenido)
                .orElseThrow(() -> new ElementoNoEncontradoExcepcion(CONTENIDO_NO_ENCONTRADO)))
                .collect(Collectors.toList());
    }

    public List<ContenidoBusquedaDTO> buscarPorTitulo(String titulo){
        return this.contenidoMapper.contenidosAContenidosBusquedaDTO(this.contenidoRepositorio.findByTitulo(titulo));
    }

    public List<ProyeccionContenidosBusqueda> buscarPorGenero(Long id){
        return this.contenidoRepositorio.buscarPorGenero(id);
    }

    public List<ProyeccionContenidosBusqueda> buscarPorOrdenacion(String tipoOrdenacion){
        return this.contenidoRepositorio.buscarPorOrdenacion(tipoOrdenacion);
    }
}
