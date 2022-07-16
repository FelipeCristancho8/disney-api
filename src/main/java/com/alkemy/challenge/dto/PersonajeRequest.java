package com.alkemy.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class PersonajeRequest {


    @NotBlank(message = "Nombre es obligatorio")
    @JsonProperty("nombre")
    private String nombre;

    @NotNull(message = "Fecha de nacimiento es obligatoria")
    @JsonProperty("fechaNacimiento")
    private LocalDate fechaNacimiento;

    @NotNull(message = "Peso es obligatorio")
    @JsonProperty("peso")
    private float peso;

    @JsonProperty("historia")
    private String historia;

    @JsonProperty("contenidoAsociado")
    private Set<Long> contenidoAsociado;
}
