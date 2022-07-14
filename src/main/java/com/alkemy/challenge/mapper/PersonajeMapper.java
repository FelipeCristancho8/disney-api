package com.alkemy.challenge.mapper;

import com.alkemy.challenge.dto.*;
import com.alkemy.challenge.entidad.Personaje;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonajeMapper {
    PersonajeDTO personajeAPersonajeDTO(Personaje personaje);
    List<PersonajeDTO> personajesAPersonajesDTO(List<Personaje> listaPersonajes);
    PersonajeBasicoDTO personajeAPersonajeBasicoDTO(Personaje personaje);
    List<PersonajeBasicoDTO> personajesAPersonajesBasicosDTO(List<Personaje> listaPersonajes);
    @Mapping(target = "contenidoAsociado",ignore = true)
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "imagen",ignore = true)
    Personaje personajeRequestAPersonaje(PersonajeRequest personajeRequest);

    PersonajeResponse personajeAPersonajeResponse(Personaje personaje);
}
