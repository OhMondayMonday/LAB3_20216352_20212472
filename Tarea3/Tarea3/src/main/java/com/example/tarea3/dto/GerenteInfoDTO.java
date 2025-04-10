package com.example.tarea3.dto;

public class GerenteInfoDTO {
    private String departamento;
    private String nombreGerente;
    private Double salario;

    public GerenteInfoDTO(String departamento, String nombreGerente, Double salario) {
        this.departamento = departamento;
        this.nombreGerente = nombreGerente;
        this.salario = salario;
    }

    // Getters y Setters
}