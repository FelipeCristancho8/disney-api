package com.alkemy.challenge.utilidades;

import java.time.LocalDate;
import java.time.Period;

public class CalculosFechas {

    public static int calcularEdad(LocalDate fechaInicio){
        return Period.between(fechaInicio,LocalDate.now()).getYears();
    }
}
