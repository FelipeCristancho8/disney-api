package com.alkemy.challenge.servicio;

import com.alkemy.challenge.dto.PersonajeDTO;
import com.alkemy.challenge.dto.PersonajeRequest;
import com.alkemy.challenge.dto.PersonajeResponse;
import com.alkemy.challenge.entidad.Contenido;
import com.alkemy.challenge.entidad.Personaje;
import com.alkemy.challenge.mapper.PersonajeMapper;
import com.alkemy.challenge.repositorio.PersonajeRepositorio;
import com.alkemy.challenge.utilidades.SubidaArchivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonajeServicio {

    @Autowired
    private PersonajeRepositorio personajeRepositorio;

    @Autowired
    private ContenidoServicio contenidoServicio;

    @Autowired
    private PersonajeMapper personajeMapper;

    public List<PersonajeDTO> listarPersonajes(){
        return personajeMapper.personajesAPersonajesDTO(this.personajeRepositorio.findAll());
    }

    public PersonajeResponse crearPersonaje(PersonajeRequest personaje, MultipartFile imagen){
        String rutaImagen = "";
        List<Contenido> contenidos = this.contenidoServicio.obtenerContenidos(new ArrayList<>(personaje.getContenidoAsociado()));
        Personaje personajeEntidad = personajeMapper.personajeRequestAPersonaje(personaje);
        if(contenidos != null){
            personajeEntidad.agregarContenidos(contenidos);
        }
        if(!imagen.isEmpty()){
            rutaImagen = SubidaArchivos.subirImagen(imagen);
            personajeEntidad.setImagen(rutaImagen);
        }
        //this.personajeRepositorio.save(personajeEntidad);
        return personajeMapper.personajeAPersonajeResponse(personajeEntidad);
    }

    public List<Personaje> obtenerPersonajes(List<Long> personajes){
        if(verificarSiExistenPersonajes(personajes)){
            return personajes.stream().map((idPersonaje -> this.personajeRepositorio.findById(idPersonaje).orElse(null)))
                    .collect(Collectors.toList());
        }
        //TODO enviar excepcion
        return null;
    }
    private boolean verificarSiExistenPersonajes(List<Long> idPersonajes){
        return idPersonajes.stream().allMatch(id ->this.personajeRepositorio.existsById(id));
    }


    public void agregarContenidoAPersonajes(Contenido contenido, List<Personaje> personajes){
        personajes.forEach(personaje -> {
            personaje.agregarContenido(contenido);
            this.personajeRepositorio.save(personaje);
        });
    }

    public List<PersonajeDTO> buscarPorNombre(String nombre){
        return this.personajeMapper.personajesAPersonajesDTO(this.personajeRepositorio.findByNombre(nombre));
    }

}
