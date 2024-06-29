package com.codigo.ms_Ccallo_Andrada_Hexagonal.mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;



@Service
public class EmpleadoMapper {
    private static  final ModelMapper modelMapper=new ModelMapper();

    public <T, D> void updateEntityFromDto(D dto, T entity) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(dto, entity);
    }
}
