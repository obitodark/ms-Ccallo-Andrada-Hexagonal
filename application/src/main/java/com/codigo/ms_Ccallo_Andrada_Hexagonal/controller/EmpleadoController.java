package com.codigo.ms_Ccallo_Andrada_Hexagonal.controller;

import com.codigo.ms_Ccallo_Andrada_Hexagonal.aggregates.constants.Constants;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.dto.EmpleadoDTO;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.ports.in.EmpleadoServiceIn;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.response.ResponseBase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoServiceIn empleadoServiceIn;

    @PostMapping
    public ResponseEntity<ResponseBase> createEmpleado(@RequestBody EmpleadoDTO empleadoDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empleadoServiceIn.createEmpladoIn(empleadoDTO));

    }
    @GetMapping
    public ResponseEntity<ResponseBase> getAllEmpleados(){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(empleadoServiceIn.getAllEmpleadoIn());
    }
    @GetMapping("/{numDoc}")
    public ResponseEntity<ResponseBase> getById(@PathVariable String numDoc){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(empleadoServiceIn.getByNumDocIn(numDoc));
    }

    @PutMapping("/{numDoc}")
    public ResponseEntity<ResponseBase> updateById(@PathVariable String numDoc,@RequestBody EmpleadoDTO empleadoDTO){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(empleadoServiceIn.updateEmpleadoIn(numDoc,empleadoDTO));
    }
    @DeleteMapping("/{numDoc}")
    public ResponseEntity<ResponseBase> deleteById(@PathVariable String numDoc){
        empleadoServiceIn.deleteEmpleadoIn(numDoc);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new ResponseBase(Constants.CODIGO_EXITO,Constants.MENSAJE_EXITO, Optional.empty()));
    }
}
