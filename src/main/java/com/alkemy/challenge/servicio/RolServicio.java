package com.alkemy.challenge.servicio;

import com.alkemy.challenge.entidad.Rol;
import com.alkemy.challenge.enums.RolNombre;
import com.alkemy.challenge.repositorio.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolServicio {

    @Autowired
    private RolRepositorio rolRepositorio;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return this.rolRepositorio.findByRolNombre(rolNombre);
    }

    public void save(Rol rol){
        this.rolRepositorio.save(rol);
    }
}
