package com.alkemy.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginDTO {

    @NotBlank(message = "nombreUsuario es obligatorio")
    @JsonProperty("nombreUsuario")
    private String nombreUsuario;
    @NotBlank(message = "password es obligatorio")
    @JsonProperty("password")
    private String password;
}
