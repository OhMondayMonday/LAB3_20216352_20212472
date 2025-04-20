package com.example.tarea3.controllers;

import com.example.tarea3.models.Employee;
import com.example.tarea3.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empleados")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Mostrar lista de empleados
    @GetMapping
    public String listarEmpleados(Model model) {
        model.addAttribute("empleados", employeeRepository.findAll());
        return "employee/index"; // Carga templates/employee/index.html
    }

    // Mostrar formulario para nuevo empleado
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("empleado", new Employee());
        return "employee/form"; // Carga templates/employee/form.html
    }

    // Guardar nuevo empleado
    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute Employee empleado) {
        employeeRepository.save(empleado);
        return "redirect:/empleados";
    }
}
