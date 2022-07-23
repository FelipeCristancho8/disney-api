package com.alkemy.challenge.excepcion;

public class ElementoDuplicadoExcepcion extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ElementoDuplicadoExcepcion(String mensaje) {
        super(mensaje);
    }
}
