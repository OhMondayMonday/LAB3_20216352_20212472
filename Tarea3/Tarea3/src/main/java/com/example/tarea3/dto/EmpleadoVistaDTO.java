package com.example.tarea3.dto;

public class EmpleadoVistaDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String puesto;
    private String departamento;
    private String ciudad;

    // constructor
    public EmpleadoVistaDTO(Integer id, String nombre, String apellido, String puesto, String departamento, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.departamento = departamento;
        this.ciudad = ciudad;
    }

    // getters
    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getPuesto() { return puesto; }
    public String getDepartamento() { return departamento; }
    public String getCiudad() { return ciudad; }
}
