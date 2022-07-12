package com.alkemy.challenge.repositorio;

import com.alkemy.challenge.entidad.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje,Long> {
}
