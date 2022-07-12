package com.alkemy.challenge.mapper;

import com.alkemy.challenge.dto.ContenidoBasicoDTO;
import com.alkemy.challenge.dto.ContenidoDTO;
import com.alkemy.challenge.entidad.Contenido;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContenidoMapper {

    ContenidoDTO ContenidoAContenidoDTO(Contenido contenido);
    List<ContenidoDTO> ContenidosAContenidosDTO(List<Contenido> contenido);

    ContenidoBasicoDTO ContenidoAContenidoBasicoDTO(Contenido contenido);

    List<ContenidoBasicoDTO> ContenidosAContenidosBasicosDTO(List<Contenido> listaContenidos);
}
