package com.example.tarea3.controllers;

import com.example.tarea3.dto.DepartamentoPorCiudadDTO;
import com.example.tarea3.dto.GerenteExperienciaDTO;
import com.example.tarea3.models.Employee;
import com.example.tarea3.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public String reportIndex(Model model) {
        try {
            // Añadir estadísticas generales para el dashboard
            model.addAttribute("totalEmployees", reportService.getTotalEmployees());
            model.addAttribute("totalManagers", reportService.getTotalManagers());
            model.addAttribute("highSalaryCount", reportService.getHighSalaryEmployeesCount());
            return "reports/report-dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar el dashboard: " + e.getMessage());
            return "error/general-error";
        }
    }

    @GetMapping("/high-salary-employees")
    public String highSalaryEmployees(Model model) {
        try {
            List<Employee> employees = reportService.getHighSalaryEmployees();
            model.addAttribute("employees", employees);
            model.addAttribute("title", "Empleados con Salario Alto");
            model.addAttribute("subtitle", "Empleados con salario mayor a $15,000");
            return "reports/high-salary-report";
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar el reporte: " + e.getMessage());
            return "error/general-error";
        }
    }

    @GetMapping("/departments-by-city")
    public String departmentsByCity(Model model) {
        try {
            List<DepartamentoPorCiudadDTO> results = reportService.getDepartmentsByCity();
            model.addAttribute("results", results);
            model.addAttribute("title", "Departamentos por Ciudad");
            model.addAttribute("subtitle", "Ciudades con más de 3 empleados en departamentos");
            return "reports/departments-by-city-report";
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar el reporte: " + e.getMessage());
            return "error/general-error";
        }
    }

    @GetMapping("/experienced-managers")
    public String experiencedManagers(Model model) {
        try {
            List<GerenteExperienciaDTO> managers = reportService.getExperiencedManagers();
            model.addAttribute("managers", managers);
            model.addAttribute("title", "Gerentes Experimentados");
            model.addAttribute("subtitle", "Gerentes con experiencia mayor a 5 años");
            return "reports/experienced-managers-report";
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar el reporte: " + e.getMessage());
            return "error/general-error";
        }
    }
}

