package com.codigo.ms_Ccallo_Andrada_Hexagonal.aggregates.exception.advice;

import com.codigo.ms_Ccallo_Andrada_Hexagonal.aggregates.exception.personalize.AlreadyExistsException;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.aggregates.exception.personalize.NotContentException;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.aggregates.exception.personalize.NotFoundException;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.response.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.Optional;
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase> handlerExeption(Exception ex){
      ResponseBase responseBase=ResponseBase.builder()
              .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
              .message("Internal Server Error"+ex.getMessage())
              .data(Optional.empty())
              .build();
        return new ResponseEntity<>(responseBase, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseBase> handlerNullPointerExeption(NullPointerException ex){
        ResponseBase responseBase=ResponseBase.builder()
                .code(HttpStatus.CONFLICT.value())
                .message("Error :"+ex.getMessage())
                .data(Optional.empty())
                .build();
        return new ResponseEntity<>(responseBase, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseBase> handlerIOExeption(IOException ex){
        ResponseBase responseBase=ResponseBase.builder()
                .code(HttpStatus.NOT_ACCEPTABLE.value())
                .message("Error :"+ex.getMessage())
                .data(Optional.empty())
                .build();
        return new ResponseEntity<>(responseBase, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseBase> handlerRuntimeExeption(RuntimeException ex){
        ResponseBase responseBase=ResponseBase.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Error :"+ex.getMessage())
                .data(Optional.empty())
                .build();
        return new ResponseEntity<>(responseBase, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ResponseBase> handlerAlreadyExistsExeption(AlreadyExistsException ex){
        ResponseBase responseBase=ResponseBase.builder()
                .code(HttpStatus.CONFLICT.value())
                .message("Error :" +ex.getMessage())
                .data(Optional.empty())
                .build();
        return new ResponseEntity<>(responseBase, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseBase> handlerNotFoundException(NotFoundException ex){
        ResponseBase responseBase=ResponseBase.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message("Error :" +ex.getMessage())
                .data(Optional.empty())
                .build();
        return new ResponseEntity<>(responseBase, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotContentException.class)
    public ResponseEntity<ResponseBase> handlerNotContentException(NotContentException ex){
        ResponseBase responseBase=ResponseBase.builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Error :" +ex.getMessage())
                .data(Optional.empty())
                .build();
        return new ResponseEntity<>(responseBase, HttpStatus.NO_CONTENT);
    }
}
