package com.codigo.ms_Ccallo_Andrada_Hexagonal.dao;

import com.codigo.ms_Ccallo_Andrada_Hexagonal.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRespository extends JpaRepository<EmpleadoEntity,Long> {
    Optional<List<EmpleadoEntity>> findByEstado(Boolean estado);
    EmpleadoEntity findByNumDoc(String numDoc);
}
