package com.codigo.ms_Ccallo_Andrada_Hexagonal.ports.out;

import com.codigo.ms_Ccallo_Andrada_Hexagonal.dto.EmpleadoDTO;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.response.ResponseBase;

public interface EmpleadoServiceOut {
    ResponseBase createEmpladoOut(EmpleadoDTO empleadoDTO);
    ResponseBase getByNumDocOut(String numDoc);
    ResponseBase getAllEmpleadoOut();
    ResponseBase updateEmpleadoOut(String numDoc,EmpleadoDTO empleadoDTO);
    void deleteEmpleadoOut(String numDoc);
}
