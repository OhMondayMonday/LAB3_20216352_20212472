package com.example.tarea3.dto;

public class DepartamentoPorCiudadDTO {
    private String pais;
    private String ciudad;
    private long cantidadDepartamentos;

    public DepartamentoPorCiudadDTO(String pais, String ciudad, long cantidadDepartamentos) {
        this.pais = pais;
        this.ciudad = ciudad;
        this.cantidadDepartamentos = cantidadDepartamentos;
    }

    // Getters
    public String getPais() { return pais; }
    public String getCiudad() { return ciudad; }
    public long getCantidadDepartamentos() { return cantidadDepartamentos; }
}
