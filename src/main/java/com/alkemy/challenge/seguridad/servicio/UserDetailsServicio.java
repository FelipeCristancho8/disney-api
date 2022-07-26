package com.alkemy.challenge.seguridad.servicio;

import com.alkemy.challenge.excepcion.ElementoNoEncontradoExcepcion;
import com.alkemy.challenge.entidad.Usuario;
import com.alkemy.challenge.entidad.UsuarioPrincipal;
import com.alkemy.challenge.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServicio implements UserDetailsService {

    private static String USUARIO_NO_ENCONTRADO = "El usuario no existe";

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioServicio.getByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new ElementoNoEncontradoExcepcion(USUARIO_NO_ENCONTRADO));
        return UsuarioPrincipal.build(usuario);
    }
}
