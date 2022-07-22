package com.alkemy.challenge.repositorio;

import com.alkemy.challenge.dto.proyeccion.ProyeccionContenidosBusqueda;
import com.alkemy.challenge.entidad.Contenido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContenidoRepositorio extends JpaRepository<Contenido,Long> {

    public List<Contenido> findByTitulo(String titulo);

    @Query(value = "SELECT IMAGEN, FECHA_CREACION as fechaCreacion, TITULO FROM CONTENIDOS c JOIN GENERO_CONTENIDO gc " +
            "ON gc.ID_CONTENIDO = c.ID AND gc.ID_GENERO = :id", nativeQuery = true)
    public List<ProyeccionContenidosBusqueda> buscarPorGenero(Long id);

    @Query(value = "SELECT IMAGEN, FECHA_CREACION as fechaCreacion, TITULO FROM CONTENIDOS c ORDER BY " +
            "case when :tipoOrdenacion = 'ASC' THEN fechaCreacion END ASC," +
            "case when :tipoOrdenacion = 'DESC' THEN fechaCreacion END DESC",
            nativeQuery = true)
    public List<ProyeccionContenidosBusqueda> buscarPorOrdenacion(String tipoOrdenacion);
}
