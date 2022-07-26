package com.alkemy.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("nombreUsuario")
    private String nombreUsuario;

    @JsonProperty("email")
    private String email;
}
