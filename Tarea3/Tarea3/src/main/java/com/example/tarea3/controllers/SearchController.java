package com.example.tarea3.controllers;

import com.example.tarea3.dto.DepartamentoPorCiudadDTO;
import com.example.tarea3.dto.GerenteExperienciaDTO;
import com.example.tarea3.models.Employee;
import com.example.tarea3.repositories.DepartmentRepository;
import com.example.tarea3.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public SearchController(EmployeeRepository employeeRepository,
                            DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public String indiceReportes() {
        return "search/indice";
    }

    @GetMapping("/salarios-altos")
    public String empleadosConAltoSalario(Model model) {
        List<Employee> empleados = employeeRepository.findBySalaryGreaterThan(15000);
        model.addAttribute("employee", empleados);
        return "search/salarios_altos";
    }

    @GetMapping("/departamentos-por-ciudad")
    public String reporteDepartamentosPorCiudad(Model model) {
        List<DepartamentoPorCiudadDTO> resultados = departmentRepository.departamentosConMasDeTresEmpleados();
        model.addAttribute("resultados", resultados);
        return "search/departamentos_por_ciudad";
    }

    @GetMapping("/gerentes-experimentados")
    public String reporteGerentesConExperiencia(Model model) {
        List<GerenteExperienciaDTO> gerentes = departmentRepository.gerentesConExperienciaMayorA5();
        model.addAttribute("gerentes", gerentes);
        return "search/gerentes_experimentados";
    }

}

