package com.alkemy.challenge.mapper;

import com.alkemy.challenge.dto.*;
import com.alkemy.challenge.entidad.Contenido;
import com.alkemy.challenge.entidad.Personaje;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PersonajeMapper {
    PersonajeDTO PersonajeAPersonajeDTO(Personaje personaje);
    List<PersonajeDTO> PersonajesAPersonajesDTO(List<Personaje> listaPersonajes);
    PersonajeBasicoDTO PersonajeAPersonajeBasicoDTO(Personaje personaje);
    List<PersonajeBasicoDTO> PersonajesAPersonajesBasicosDTO(List<Personaje> listaPersonajes);
    @Mapping(target = "contenidoAsociado",ignore = true)
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "imagen",ignore = true)
    Personaje PersonajeRequestAPersonaje(PersonajeRequest personajeRequest);

    PersonajeResponse PersonajeAPersonajeResponse(Personaje personaje);
}
