package com.alkemy.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ContenidoEditadoRequest {

    @JsonProperty("imagen")
    private String imagen;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("fechaCreacion")
    private LocalDate fechaCreacion;

    @JsonProperty("calificacion")
    private Byte calificacion;
}
