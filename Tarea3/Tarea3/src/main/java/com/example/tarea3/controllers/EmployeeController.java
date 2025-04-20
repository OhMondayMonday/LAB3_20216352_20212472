package com.example.tarea3.controllers;

import com.example.tarea3.models.Employee;
import com.example.tarea3.repositories.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empleados")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // Mostrar lista de empleados con ciudad (usando EntityManager)
    @GetMapping
    public String listarEmpleados(Model model) {
        List<Employee> empleados = employeeRepository.findAll();

        // Añadir ciudad manualmente desde la tabla locations
        for (Employee emp : empleados) {
            if (emp.getDepartamento() != null) {
                Query query = entityManager.createNativeQuery(
                        "SELECT l.city FROM locations l " +
                                "JOIN departments d ON d.location_id = l.location_id " +
                                "WHERE d.department_id = ?1"
                );
                query.setParameter(1, emp.getDepartamento());
                List<String> resultado = query.getResultList();
                emp.setCiudad(resultado.isEmpty() ? "Sin ciudad" : resultado.get(0));
            } else {
                emp.setCiudad("Sin depto");
            }
        }

        model.addAttribute("empleados", empleados);
        return "employee/index";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editarEmpleado(@PathVariable Integer id, Model model) {
        model.addAttribute("empleado", employeeRepository.findById(id).orElse(null));
        return "employee/form";
    }

    // Eliminar empleado
    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Integer id) {
        employeeRepository.deleteById(id);
        return "redirect:/empleados";
    }

    // Mostrar formulario para nuevo empleado
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("empleado", new Employee());
        return "employee/form";
    }


    // Guardar empleado (nuevo o editado)
    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute Employee empleado) {
        employeeRepository.save(empleado);
        return "redirect:/empleados";
    }
}
