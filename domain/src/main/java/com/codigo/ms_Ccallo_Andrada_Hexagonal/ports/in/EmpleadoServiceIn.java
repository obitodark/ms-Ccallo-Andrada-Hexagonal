package com.codigo.ms_Ccallo_Andrada_Hexagonal.ports.in;

import com.codigo.ms_Ccallo_Andrada_Hexagonal.dto.EmpleadoDTO;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.response.ResponseBase;

public interface EmpleadoServiceIn {
    ResponseBase createEmpladoIn(EmpleadoDTO empleadoDTO);
    ResponseBase getByNumDocIn(String numDoc);
    ResponseBase getAllEmpleadoIn();
    ResponseBase updateEmpleadoIn(String numDoc,EmpleadoDTO empleadoDTO);
    void deleteEmpleadoIn(String numDoc);
}
