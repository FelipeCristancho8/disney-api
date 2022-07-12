package com.alkemy.challenge.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Personajes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String imagen;

    @NotBlank
    private String nombre;

    @NotNull
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @NotNull
    private float peso;

    @Column(length = 255)
    private String historia;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "personaje_contenido", joinColumns = @JoinColumn(name = "id_personaje"),
               inverseJoinColumns = @JoinColumn(name = ("id_contenido")))
    private Set<Contenido> contenidoAsociado = new HashSet<>();

    public void agregarContenido(Contenido contenido){
        this.contenidoAsociado.add(contenido);
    }
}
