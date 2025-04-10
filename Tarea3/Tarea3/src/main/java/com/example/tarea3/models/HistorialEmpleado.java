package com.example.tarea3.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class HistorialEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcionTrabajo;

    private LocalDate fecha;

    @ManyToOne
    private Employee empleado;

    public HistorialEmpleado() {}

    // Getters y Setters
}
