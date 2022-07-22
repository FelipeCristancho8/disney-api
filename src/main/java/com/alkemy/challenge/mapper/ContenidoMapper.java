package com.alkemy.challenge.mapper;

import com.alkemy.challenge.dto.*;
import com.alkemy.challenge.entidad.Contenido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContenidoMapper {

    ContenidoDTO contenidoAContenidoDTO(Contenido contenido);
    List<ContenidoDTO> contenidosAContenidosDTO(List<Contenido> contenido);

    ContenidoBasicoDTO ContenidoAContenidoBasicoDTO(Contenido contenido);

    List<ContenidoBasicoDTO> contenidosAContenidosBasicosDTO(List<Contenido> listaContenidos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imagen", ignore = true)
    @Mapping(target = "personajesAsociados", ignore = true)
    @Mapping(target = "generosAsociados", ignore = true)
    Contenido contenidoRequestAContenido(ContenidoRequest contenidoRequest);

    ContenidoResponse contenidoAContenidoResponse(Contenido contenido);

    List<ContenidoBusquedaDTO> contenidosAContenidosBusquedaDTO(List<Contenido> contenidos);
}
