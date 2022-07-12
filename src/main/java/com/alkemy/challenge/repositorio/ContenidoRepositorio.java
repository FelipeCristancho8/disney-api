package com.alkemy.challenge.repositorio;

import com.alkemy.challenge.entidad.Contenido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenidoRepositorio extends JpaRepository<Contenido,Long> {
}
