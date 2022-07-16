package com.alkemy.challenge.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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
    @Column(nullable = false)
    private String imagen;

    @NotBlank(message = "Titulo es obligatorio")
    @Column(nullable = false)
    private String titulo;

    @Column(name = "fecha_creacion", nullable = false)
    @NotNull(message = "Fecha de creacion es obligatoria")
    private LocalDate fechaCreacion;

    @NotNull(message = "Calificacion es obligatorio")
    @Range(min = 1, max = 5, message = "La calificacion debe estar entre 1 y 5")
    @Column(nullable = false)
    private byte calificacion;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "contenidoAsociado")
    private Set<Personaje> personajesAsociados = new HashSet<>();

    /*@JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "contenidoAsociado")*/
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "genero_contenido", joinColumns = @JoinColumn(name = "id_contenido"),
            inverseJoinColumns = @JoinColumn(name = ("id_genero")))
    private Set<Genero> generosAsociados = new HashSet<>();

    public void agregarPersonaje(Personaje personaje){
        this.personajesAsociados.add(personaje);
    }

    public void agregarGenero(Genero genero){
        this.generosAsociados.add(genero);
    }

    public void agregarGeneros(List<Genero> generos){
        generos.forEach( genero -> this.generosAsociados.add(genero));
    }

    public void agregarPersonajes(List<Personaje> personajes){
        personajes.forEach(personaje -> this.personajesAsociados.add(personaje));
    }
}
