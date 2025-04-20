package com.example.tarea3.dto;
import java.math.BigDecimal;

public class GerenteExperienciaDTO {
    private String nombreDepartamento;
    private String nombreGerente;
    private BigDecimal salario;

    public GerenteExperienciaDTO(String nombreDepartamento, String nombreGerente, BigDecimal salario) {
        this.nombreDepartamento = nombreDepartamento;
        this.nombreGerente = nombreGerente;
        this.salario = salario;
    }

    public String getNombreDepartamento() { return nombreDepartamento; }
    public String getNombreGerente() { return nombreGerente; }
    public BigDecimal getSalario() { return salario; }
}
