package com.alkemy.challenge.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contenidos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Imagen es obligatoria")
    private String imagen;

    @NotBlank(message = "Titulo es obligatorio")
    private String titulo;

    @Column(name = "fecha_creacion")
    @NotNull(message = "Fecha de creacion es obligatoria")
    private LocalDate fechaCreacion;

    @NotNull(message = "Calificacion es obligatorio")
    @Range(min = 1, max = 5, message = "La calificacion debe estar entre 1 y 5")
    private byte calificacion;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "contenidoAsociado")
    private Set<Personaje> personajesAsociados = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "contenidoAsociado")
    private Set<Genero> generosAsociados = new HashSet<>();

    public void agregarPersonaje(Personaje personaje){
        this.personajesAsociados.add(personaje);
    }

    public void agregarGenero(Genero genero){
        this.generosAsociados.add(genero);
    }
}
