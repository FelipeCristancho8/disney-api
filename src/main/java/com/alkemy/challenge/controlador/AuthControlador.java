package com.alkemy.challenge.controlador;

import com.alkemy.challenge.dto.UsuarioResponse;
import com.alkemy.challenge.dto.JwtDTO;
import com.alkemy.challenge.dto.LoginDTO;
import com.alkemy.challenge.dto.UsuarioDTO;
import com.alkemy.challenge.servicio.AutenticacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthControlador {

    @Autowired
    AutenticacionServicio autenticacionServicio;


    @PostMapping("/nuevo")
    public ResponseEntity<UsuarioResponse> registrarUsuario(@Valid @RequestBody UsuarioDTO nuevoUsuario){
        return new ResponseEntity(this.autenticacionServicio.registrarUsuario(nuevoUsuario), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> loginUsuario(@Valid @RequestBody LoginDTO loginUsuario){
        return new ResponseEntity(this.autenticacionServicio.login(loginUsuario), HttpStatus.OK);
    }

}
