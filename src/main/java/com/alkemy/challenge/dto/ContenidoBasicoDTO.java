package com.alkemy.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContenidoBasicoDTO {

    @JsonProperty("titulo")
    private String titulo;
}
