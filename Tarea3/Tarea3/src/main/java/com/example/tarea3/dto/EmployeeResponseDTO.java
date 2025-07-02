package com.example.tarea3.dto;

public class EmployeeResponseDTO {
    private long totalEmployees;
    private int highSalaryEmployees;
    private int totalManagers;

    public EmployeeResponseDTO() {}

    public EmployeeResponseDTO(long totalEmployees, int highSalaryEmployees, int totalManagers) {
        this.totalEmployees = totalEmployees;
        this.highSalaryEmployees = highSalaryEmployees;
        this.totalManagers = totalManagers;
    }

    // Getters y setters
    public long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public int getHighSalaryEmployees() {
        return highSalaryEmployees;
    }

    public void setHighSalaryEmployees(int highSalaryEmployees) {
        this.highSalaryEmployees = highSalaryEmployees;
    }

    public int getTotalManagers() {
        return totalManagers;
    }

    public void setTotalManagers(int totalManagers) {
        this.totalManagers = totalManagers;
    }
}
