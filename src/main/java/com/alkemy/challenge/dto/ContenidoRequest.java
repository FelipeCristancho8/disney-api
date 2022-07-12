package com.alkemy.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Set;

public class ContenidoRequest {

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("fechaCreacion")
    private LocalDate fechaCreacion;

    @JsonProperty("calificacion")
    private byte calificacion;

    @JsonProperty("personajesAsociados")
    private Set<PersonajeBasicoDTO> personajesAsociados;

    @JsonProperty("generosAsociados")
    private Set<PersonajeBasicoDTO> generosAsociados;
}
