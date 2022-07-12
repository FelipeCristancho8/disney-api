package com.alkemy.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonajeBasicoDTO {

    @JsonProperty("nombre")
    private String nombre;
}
