package com.codigo.ms_Ccallo_Andrada_Hexagonal.aggregates.exception.personalize;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
