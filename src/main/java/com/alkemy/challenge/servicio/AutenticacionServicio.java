package com.alkemy.challenge.servicio;

import com.alkemy.challenge.dto.UsuarioResponse;
import com.alkemy.challenge.excepcion.ElementoDuplicadoExcepcion;
import com.alkemy.challenge.mapper.UsuarioMapper;
import com.alkemy.challenge.dto.JwtDTO;
import com.alkemy.challenge.dto.LoginDTO;
import com.alkemy.challenge.dto.UsuarioDTO;
import com.alkemy.challenge.entidad.Rol;
import com.alkemy.challenge.entidad.Usuario;
import com.alkemy.challenge.enums.RolNombre;
import com.alkemy.challenge.seguridad.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AutenticacionServicio {

    private static final String NOMBRE_USUARIO_DUPLICADO = "Ya existe un usuario con el mismo nombre de usuario";
    private static final String EMAIL_DUPLICADO = "Ya existe un usuario con el mismo email";

    @Autowired
    UsuarioServicio usuarioServicio;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolServicio rolServicio;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UsuarioMapper usuarioMapper;

    public UsuarioResponse registrarUsuario(UsuarioDTO nuevoUsuario){
        if(usuarioServicio.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            throw new ElementoDuplicadoExcepcion(NOMBRE_USUARIO_DUPLICADO);
        if(usuarioServicio.existsByEmail(nuevoUsuario.getEmail()))
            throw new ElementoDuplicadoExcepcion(EMAIL_DUPLICADO);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolServicio.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolServicio.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        return this.usuarioMapper.UsuarioAUsuarioResponse(usuarioServicio.save(usuario));
    }

    public JwtDTO login(LoginDTO loginUsuario){
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
       return new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
    }
}
