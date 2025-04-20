package com.example.tarea3.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "vista_empleados_completa")
public class EmpleadoVista {
    @Id
    private Integer id;
    private String nombre;
    private String apellido;
    private String puesto;
    private String departamento;
    private String ciudad;
    private String pais;

    // Getters y setters
}
