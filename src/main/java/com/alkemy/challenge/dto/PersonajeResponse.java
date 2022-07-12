package com.alkemy.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class PersonajeResponse {

    @JsonProperty("imagen")
    private String imagen;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("fechaNacimiento")
    private LocalDate fechaNacimiento;

    @JsonProperty("peso")
    private float peso;

    @JsonProperty("historia")
    private String historia;

    @JsonProperty("contenidoAsociado")
    private Set<ContenidoBasicoDTO> contenidoAsociado;
}
