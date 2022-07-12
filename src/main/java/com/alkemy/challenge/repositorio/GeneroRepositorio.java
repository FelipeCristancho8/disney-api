package com.alkemy.challenge.repositorio;

import com.alkemy.challenge.entidad.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepositorio extends JpaRepository<Genero,Long> {
}
