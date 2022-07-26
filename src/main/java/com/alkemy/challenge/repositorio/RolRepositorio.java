package com.alkemy.challenge.repositorio;

import com.alkemy.challenge.entidad.Rol;
import com.alkemy.challenge.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
