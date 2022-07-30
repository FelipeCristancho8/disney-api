package com.alkemy.challenge.servicio;

import com.alkemy.challenge.dto.*;
import com.alkemy.challenge.dto.proyeccion.IProyeccionPersonajeBusqueda;
import com.alkemy.challenge.entidad.Contenido;
import com.alkemy.challenge.entidad.Personaje;
import com.alkemy.challenge.excepcion.ElementoNoEncontradoExcepcion;
import com.alkemy.challenge.mapper.PersonajeMapper;
import com.alkemy.challenge.repositorio.PersonajeRepositorio;
import com.alkemy.challenge.utilidades.SubidaArchivos;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonajeServicio {

    private static final String PERSONAJE_NO_ENCONTRADO = "Personaje no encontrado";

    @Autowired
    private PersonajeRepositorio personajeRepositorio;

    @Autowired
    private ContenidoServicio contenidoServicio;

    @Autowired
    private PersonajeMapper personajeMapper;


    public PersonajeResponse crearPersonaje(PersonajeRequest personaje, MultipartFile imagen){
        String rutaImagen = "";
        Personaje personajeEntidad = personajeMapper.personajeRequestAPersonaje(personaje);
        if(personaje.getContenidoAsociado() != null){
            List<Contenido> contenidos = this.contenidoServicio.obtenerContenidos(new ArrayList<>(personaje.getContenidoAsociado()));
            personajeEntidad.agregarContenidos(contenidos);
        }
        if(!imagen.isEmpty()){
            rutaImagen = SubidaArchivos.subirImagen(imagen);
            personajeEntidad.setImagen(rutaImagen);
        }
        this.personajeRepositorio.save(personajeEntidad);
        return personajeMapper.personajeAPersonajeResponse(personajeEntidad);
    }

    public PersonajeDTO editarPersonaje(Long idPersonaje, PersonajeEditadoRequest personaje, MultipartFile imagen){
        String rutaImagen = "";
        Personaje personajeAEditar = this.personajeRepositorio.findById(idPersonaje)
                .orElseThrow(() -> new ElementoNoEncontradoExcepcion(PERSONAJE_NO_ENCONTRADO));
        if(!imagen.isEmpty()){
            rutaImagen = SubidaArchivos.subirImagen(imagen);
            personajeAEditar.setImagen(rutaImagen);
        }
        this.mapearCamposValidos(personaje,personajeAEditar);
        return personajeMapper.personajeAPersonajeDTO(this.personajeRepositorio.save(personajeAEditar));
    }

    private void mapearCamposValidos(PersonajeEditadoRequest personajeRequest, Personaje personaje){
        if(!StringUtils.isBlank(personajeRequest.getNombre())){
            personaje.setNombre(personajeRequest.getNombre());
        }
        if(personajeRequest.getFechaNacimiento() != null){
            personaje.setFechaNacimiento(personajeRequest.getFechaNacimiento());
        }
        if(!StringUtils.isBlank(personajeRequest.getHistoria())){
            personaje.setHistoria(personajeRequest.getHistoria());
        }
        if(personajeRequest.getPeso() != null){
            personaje.setPeso(personajeRequest.getPeso());
        }
    }

    public void eliminarPersonaje(Long idPersonaje){
        if(!this.personajeRepositorio.existsById(idPersonaje)){
            throw new ElementoNoEncontradoExcepcion(PERSONAJE_NO_ENCONTRADO);
        }
        this.personajeRepositorio.deleteById(idPersonaje);
    }

    public List<Personaje> obtenerPersonajes(List<Long> personajes){
        return personajes.stream().map((idPersonaje -> this.personajeRepositorio.findById(idPersonaje)
                .orElseThrow(() -> new ElementoNoEncontradoExcepcion(PERSONAJE_NO_ENCONTRADO))))
                .collect(Collectors.toList());
    }

    public Personaje obtenerPersonaje(Long id){
        return this.personajeRepositorio.findById(id)
                .orElseThrow(() -> new ElementoNoEncontradoExcepcion(PERSONAJE_NO_ENCONTRADO));
    }

    public void agregarContenidoAPersonajes(Contenido contenido, List<Personaje> personajes){
        personajes.forEach(personaje -> {
            personaje.agregarContenido(contenido);
            this.personajeRepositorio.save(personaje);
        });
    }

    public void agregarPersonajeAContenido(Contenido contenido, Personaje personaje){
        personaje.agregarContenido(contenido);
        this.personajeRepositorio.save(personaje);
    }

    public void eliminarPersonajeDeContenido(Contenido contenido, Personaje personaje){
        personaje.eliminarContenido(contenido);
        this.personajeRepositorio.save(personaje);
    }

    public List<PersonajeDTO> obtenerDetallePersonajes(){
        return personajeMapper.personajesAPersonajesDTO(this.personajeRepositorio.findAll());
    }

    public PersonajeDTO obtenerDetallePersonaje(Long idPersonaje){
        return this.personajeMapper.personajeAPersonajeDTO(this.personajeRepositorio.findById(idPersonaje)
                .orElseThrow( () -> new ElementoNoEncontradoExcepcion(PERSONAJE_NO_ENCONTRADO)));
    }

    public List<PersonajeBusquedaDTO> obtenerPersonajes(){
        return this.personajeMapper.personajesAPersonajesBusquedaDTO(this.personajeRepositorio.findAll());
    }

    public List<PersonajeBusquedaDTO> buscarPorNombre(String nombre){
        return this.personajeMapper.personajesAPersonajesBusquedaDTO(this.personajeRepositorio.findByNombre(nombre));
    }

    public List<IProyeccionPersonajeBusqueda> buscarPorEdad(int edad){
        return this.personajeRepositorio.buscarPorEdad(edad);
    }

    public List<IProyeccionPersonajeBusqueda> buscarPorContenido(Long id){
        return this.personajeRepositorio.buscarPorContenido(id);
    }

}
