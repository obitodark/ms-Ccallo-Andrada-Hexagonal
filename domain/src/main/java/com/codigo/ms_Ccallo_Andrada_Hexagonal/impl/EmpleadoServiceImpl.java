package com.codigo.ms_Ccallo_Andrada_Hexagonal.impl;

import com.codigo.ms_Ccallo_Andrada_Hexagonal.dto.EmpleadoDTO;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.ports.in.EmpleadoServiceIn;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.ports.out.EmpleadoServiceOut;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.response.ResponseBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoServiceIn {
    private final EmpleadoServiceOut empleadoServiceOut;
    @Override
    public ResponseBase createEmpladoIn(EmpleadoDTO empleadoDTO) {
        return empleadoServiceOut.createEmpladoOut(empleadoDTO);
    }

    @Override
    public ResponseBase getByNumDocIn(String numDoc) {
        return empleadoServiceOut.getByNumDocOut(numDoc);
    }

    @Override
    public ResponseBase getAllEmpleadoIn() {
        return empleadoServiceOut.getAllEmpleadoOut();
    }

    @Override
    public ResponseBase updateEmpleadoIn(String numDoc, EmpleadoDTO empleadoDTO) {
        return empleadoServiceOut.updateEmpleadoOut(numDoc,empleadoDTO);
    }

    @Override
    public void deleteEmpleadoIn(String numDoc) {
    empleadoServiceOut.deleteEmpleadoOut(numDoc);
    }
}
