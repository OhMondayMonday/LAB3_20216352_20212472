package com.example.tarea3.services;

import com.example.tarea3.dto.DepartamentoPorCiudadDTO;
import com.example.tarea3.dto.GerenteExperienciaDTO;
import com.example.tarea3.models.Employee;
import com.example.tarea3.repositories.DepartmentRepository;
import com.example.tarea3.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Employee> getHighSalaryEmployees() {
        return employeeRepository.findBySalaryGreaterThan(15000);
    }

    public List<DepartamentoPorCiudadDTO> getDepartmentsByCity() {
        return departmentRepository.departamentosConMasDeTresEmpleados();
    }

    public List<GerenteExperienciaDTO> getExperiencedManagers() {
        return departmentRepository.gerentesConExperienciaMayorA5();
    }

    public int getTotalEmployees() {
        return (int) employeeRepository.count();
    }

    public int getTotalManagers() {
        return employeeRepository.findAllManagers().size();
    }

    public int getHighSalaryEmployeesCount() {
        return employeeRepository.findBySalaryGreaterThan(15000).size();
    }
}
