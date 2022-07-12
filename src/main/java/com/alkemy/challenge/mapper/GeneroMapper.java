package com.alkemy.challenge.mapper;

import com.alkemy.challenge.dto.GeneroRequest;
import com.alkemy.challenge.entidad.Genero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contenidoAsociado",ignore = true)
    @Mapping(target = "imagen",ignore = true)
    Genero generoRequestAGenero(GeneroRequest genero);
}
