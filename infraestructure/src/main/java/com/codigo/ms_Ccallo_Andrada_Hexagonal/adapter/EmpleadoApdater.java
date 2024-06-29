package com.codigo.ms_Ccallo_Andrada_Hexagonal.adapter;

import com.codigo.ms_Ccallo_Andrada_Hexagonal.aggregates.constants.Constants;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.aggregates.exception.personalize.AlreadyExistsException;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.aggregates.exception.personalize.NotContentException;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.aggregates.exception.personalize.NotFoundException;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.dao.EmpleadoRespository;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.dto.EmpleadoDTO;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.entity.EmpleadoEntity;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.mapper.EmpleadoMapper;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.ports.out.EmpleadoServiceOut;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.response.ResponseBase;
import com.codigo.ms_Ccallo_Andrada_Hexagonal.response.ResponseReniec;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoApdater  implements EmpleadoServiceOut {
private final EmpleadoRespository empleadoRespository;
private final RestTemplate restTemplate;
private final String baseUrl="https://api.apis.net.pe/v2/reniec/";
private final EmpleadoMapper mapper;

@Value("${token.api}")
private String tokeApi;


    @Override
    public ResponseBase createEmpladoOut(EmpleadoDTO empleadoDTO) {
        EmpleadoEntity empleadoEntity=empleadoRespository.findByNumDoc(empleadoDTO.getNumDoc());
        if(empleadoEntity!=null){
            throw  new AlreadyExistsException("Empleado already exists  numDoc ->"+empleadoDTO.getNumDoc());

        }else {
            EmpleadoEntity empleadoBody=buildBodyDataEmpleado(empleadoDTO);
            empleadoBody.setDateCrea(getTime());
            empleadoBody.setUsuaCrea(Constants.USER_ADMIN);
            mapper.updateEntityFromDto(buildNames(empleadoDTO.getNumDoc()),empleadoBody);
            empleadoRespository.save(empleadoBody);
            return new ResponseBase(Constants.CODIGO_CREATE,Constants.MENSAJE_CREATE, Optional.of(empleadoBody));

        }

    }

    @Override
    public ResponseBase getByNumDocOut(String numDoc) {
      EmpleadoEntity empleadoEntity=empleadoRespository.findByNumDoc(numDoc);
      if(empleadoEntity!=null){
          return new ResponseBase(Constants.CODIGO_EXITO,Constants.MENSAJE_EXITO, Optional.of(empleadoEntity));
      }else {
          throw  new NotFoundException("Empleado not found  numDoc ->"+numDoc);
      }

    }

    @Override
    public ResponseBase getAllEmpleadoOut() {
        Optional<List<EmpleadoEntity>>entities=empleadoRespository.findByEstado(true);
        if (entities.isPresent() && !entities.get().isEmpty()){
           return new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_EXITO, entities);

        } else {
            throw new NotContentException("No data about Empleados");
        }

    }

    @Override
    public ResponseBase updateEmpleadoOut(String numDoc, EmpleadoDTO empleadoDTO) {

        EmpleadoEntity empleadoEntity=empleadoRespository.findByNumDoc(numDoc);
        if(empleadoEntity!=null){
            empleadoEntity.setUsuaUpdate(Constants.USER_ADMIN);
            empleadoEntity.setDateUdpate(getTime());
            EmpleadoEntity empleadoBody=buildBodyDataEmpleado(empleadoDTO);
            mapper.updateEntityFromDto(empleadoBody,empleadoEntity);
            empleadoRespository.save(empleadoEntity);

            return new ResponseBase(Constants.CODIGO_EXITO,Constants.MENSAJE_EXITO, Optional.of(empleadoEntity));
        }else {
            throw  new NotFoundException("Empleado not found  numDoc ->"+numDoc);
        }

    }

    @Override
    public void deleteEmpleadoOut(String numDoc) {
        EmpleadoEntity empleadoEntity=empleadoRespository.findByNumDoc(numDoc);
        if(empleadoEntity!=null){
         empleadoEntity.setEstado(Constants.NOT_ACTIVE);
         empleadoEntity.setDateDelete(getTime());
         empleadoEntity.setUsuaDelete(Constants.USER_ADMIN);
         empleadoRespository.save(empleadoEntity);
        }else {
            throw  new NotFoundException("Empleado not found  numDoc ->"+numDoc);
        }
    }

    private EmpleadoEntity buildBodyDataEmpleado(EmpleadoDTO empleadoDTO){
         return EmpleadoEntity.builder()
                  .edad(empleadoDTO.getEdad())
                  .cargo(empleadoDTO.getCargo())
                  .tipoDoc(empleadoDTO.getTipoDoc())
                  .numDoc(empleadoDTO.getNumDoc())
                  .departamento(empleadoDTO.getDepartamento())
                  .salario(empleadoDTO.getSalario())
                  .telefono(empleadoDTO.getTelefono())
                  .correo(empleadoDTO.getCorreo())
                  .estado(Constants.ACTIVE)
                  .direccion(empleadoDTO.getDireccion())
                  .build();
    }


    private EmpleadoEntity buildNames(String numDoc){
        ResponseReniec response=getDataReniec(numDoc);
        return EmpleadoEntity.builder()
                .nombre(response.getNombres())
                .apellido(response.getApellidoMaterno()+" "+ response.getApellidoPaterno())
                .build();
    }

    private ResponseReniec getDataReniec(String numDoc){
        ResponseEntity<ResponseReniec> response;
        String url=baseUrl+"dni?numero="+numDoc;
           response=restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(createHeader(tokeApi)),
                    ResponseReniec.class
            );
            return response.getBody();

    }


    private HttpHeaders createHeader(String token){
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("Authorization","Bearer "+token);
        return httpHeaders;
    }

    private Timestamp getTime(){
        long current = System.currentTimeMillis();
        return new Timestamp(current);
    }
}
