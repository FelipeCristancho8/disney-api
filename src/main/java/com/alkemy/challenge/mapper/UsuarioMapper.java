package com.alkemy.challenge.mapper;

import com.alkemy.challenge.dto.UsuarioResponse;
import com.alkemy.challenge.entidad.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponse UsuarioAUsuarioResponse(Usuario usuario);
}
