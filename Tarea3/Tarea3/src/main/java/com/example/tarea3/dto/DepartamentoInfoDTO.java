package com.example.tarea3.dto;

public class DepartamentoInfoDTO {
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Long getNumeroDepartamentos() {
        return numeroDepartamentos;
    }

    public void setNumeroDepartamentos(Long numeroDepartamentos) {
        this.numeroDepartamentos = numeroDepartamentos;
    }

    private String pais;
    private String ciudad;
    private Long numeroDepartamentos;

    public DepartamentoInfoDTO(String pais, String ciudad, Long numeroDepartamentos) {
        this.pais = pais;
        this.ciudad = ciudad;
        this.numeroDepartamentos = numeroDepartamentos;
    }


}
