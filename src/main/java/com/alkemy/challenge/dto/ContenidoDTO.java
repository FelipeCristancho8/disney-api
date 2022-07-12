package com.alkemy.challenge.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class ContenidoDTO {

   @JsonProperty("id")
    private Long id;

    @JsonProperty("imagen")
    private String imagen;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("fechaCreacion")
    private LocalDate fechaCreacion;

    @JsonProperty("calificacion")
    private byte calificacion;

    @JsonProperty("personajesAsociados")
    private Set<PersonajeBasicoDTO> personajesAsociados;
}
