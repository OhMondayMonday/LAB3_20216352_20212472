package com.example.tarea3.controllers;

import com.example.tarea3.models.Employee;
import com.example.tarea3.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empleados")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public String index(@RequestParam(required = false) String filtro, Model model) {
        model.addAttribute("empleados", service.listar(filtro));
        model.addAttribute("filtro", filtro);
        return "employee/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("empleado", new Employee());
        return "employee/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Employee emp) {
        service.guardar(emp);
        return "redirect:/empleados";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("empleado", service.buscarPorId(id));
        return "employee/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/empleados";
    }
}

