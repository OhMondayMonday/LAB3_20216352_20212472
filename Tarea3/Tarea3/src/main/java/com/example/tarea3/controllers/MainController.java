package com.example.tarea3.controllers;

import com.example.tarea3.dto.EmployeeResponseDTO;
import com.example.tarea3.services.EmployeeService;
import com.example.tarea3.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ReportService reportService;

    @GetMapping("/")
    public String home(Model model) {
        try {
            // Obtener estadísticas para el dashboard
            EmployeeResponseDTO stats = employeeService.getEmployeeStatistics();
            model.addAttribute("stats", stats);
            
            // Estadísticas adicionales
            model.addAttribute("totalEmployees", reportService.getTotalEmployees());
            model.addAttribute("totalManagers", reportService.getTotalManagers());
            model.addAttribute("highSalaryCount", reportService.getHighSalaryEmployeesCount());
            
            return "dashboard/main-dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar el dashboard: " + e.getMessage());
            return "error/general-error";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return home(model);
    }
}
