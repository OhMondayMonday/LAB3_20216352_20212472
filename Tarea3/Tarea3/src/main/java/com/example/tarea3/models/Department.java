package com.example.tarea3.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    // Agregado: relación con empleados
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    // Agregado: relación con location
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    // Getters
    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Employee getManager() {
        return manager;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Location getLocation() {
        return location;
    }

    // Setters
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
