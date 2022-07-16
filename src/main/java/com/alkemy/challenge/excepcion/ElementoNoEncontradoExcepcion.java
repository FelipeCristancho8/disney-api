package com.alkemy.challenge.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ElementoNoEncontradoExcepcion extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ElementoNoEncontradoExcepcion(String mensaje){
        super(mensaje);
    }
}
