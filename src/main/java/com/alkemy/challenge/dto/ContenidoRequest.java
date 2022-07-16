package com.alkemy.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;
@Getter
@Setter
public class ContenidoRequest {

    @JsonProperty("titulo")
    @NotBlank(message = "Titulo es obligatorio")
    private String titulo;

    @JsonProperty("fechaCreacion")
    @NotNull(message = "Fecha de creacion es obligatoria")
    private LocalDate fechaCreacion;

    @JsonProperty("calificacion")
    @NotNull(message = "Calificacion es obligatorio")
    @Range(min = 1, max = 5, message = "La calificacion debe estar entre 1 y 5")
    private byte calificacion;

    @JsonProperty("personajesAsociados")
    private Set<Long> personajesAsociados;

    @JsonProperty("generosAsociados")
    private Set<Long> generosAsociados;
}
