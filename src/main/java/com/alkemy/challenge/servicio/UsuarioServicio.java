package com.alkemy.challenge.servicio;

import com.alkemy.challenge.entidad.Usuario;
import com.alkemy.challenge.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return this.usuarioRepositorio.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return this.usuarioRepositorio.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return this.usuarioRepositorio.existsByEmail(email);
    }

    public Usuario save(Usuario usuario){
        return this.usuarioRepositorio.save(usuario);
    }
}
