package com.codigo.ms_Ccallo_Andrada_Hexagonal.aggregates.exception.personalize;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String message){
        super(message);
    }
}
