package com.alkemy.challenge.repositorio;

import com.alkemy.challenge.dto.proyeccion.IProyeccionPersonajeBusqueda;
import com.alkemy.challenge.entidad.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje,Long> {

    public List<Personaje> findByNombre(String nombre);

    @Query(value = "SELECT imagen,nombre FROM PERSONAJES p, PERSONAJE_CONTENIDO pc WHERE pc.ID_CONTENIDO = :id AND " +
            "pc.ID_PERSONAJE  = p.ID", nativeQuery = true)
    public List<IProyeccionPersonajeBusqueda> buscarPorContenido(Long id);

    @Query(value = "SELECT imagen, nombre FROM PERSONAJES p WHERE FLOOR(MONTHS_BETWEEN(SYSDATE,FECHA_NACIMIENTO)/12) = :edad",
            nativeQuery = true)
    public List<IProyeccionPersonajeBusqueda> buscarPorEdad(int edad);
}
