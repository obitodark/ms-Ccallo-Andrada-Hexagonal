package com.codigo.ms_Ccallo_Andrada_Hexagonal.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
@Entity
@Table(name="empleados")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpleadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="empleado_id")
    private Long id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String cargo;
    private String tipoDoc;
    private String numDoc;
    private String departamento;
    private Double salario;
    private String telefono;
    private String correo;
    private Boolean estado;
    private String direccion;
    private Timestamp dateCrea;
    private String usuaCrea;
    private Timestamp dateUdpate;
    private String usuaUpdate;
    private Timestamp dateDelete;
    private String usuaDelete;

}
