package com.alkemy.challenge.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "generos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String imagen;

    @NotBlank
    private String nombre;

    /*@JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "genero_contenido", joinColumns = @JoinColumn(name = "id_genero"),
            inverseJoinColumns = @JoinColumn(name = ("id_contenido")))*/
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "generosAsociados")
    private Set<Contenido> contenidoAsociado = new HashSet<>();

    public void agregarContenido(Contenido contenido){
        this.contenidoAsociado.add(contenido);
    }
}
