package com.example.tarea3.controllers;

import com.example.tarea3.models.Department;
import com.example.tarea3.models.Employee;
import com.example.tarea3.models.Job;
import com.example.tarea3.repositories.DepartmentRepository;
import com.example.tarea3.repositories.EmployeeRepository;
import com.example.tarea3.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobRepository jobRepository;


    @GetMapping
    public String listEmployees(@RequestParam(name = "filter", required = false) String filter, Model model) {
        List<Employee> list;
        if (filter != null && !filter.isEmpty()) {
            list = employeeRepository.searchByFilter(filter);
        } else {
            list = employeeRepository.findAll();
        }
        model.addAttribute("employeeList", list);
        model.addAttribute("filter", filter);
        return "employee/list";

    }

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("managers", employeeRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("jobs", jobRepository.findAll()); // solo si ya lo tienes
        return "employee/create";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Integer id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));

        model.addAttribute("employee", employee);
        model.addAttribute("jobs", jobRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("managers", employeeRepository.findAll());

        return "employee/edit";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee,
                                 @RequestParam String jobId,
                                 @RequestParam Integer departmentId,
                                 @RequestParam Integer managerId) {

        Job job = jobRepository.findById(jobId).orElse(null);
        Department department = departmentRepository.findById(departmentId).orElse(null);
        Employee manager = employeeRepository.findById(managerId).orElse(null);

        employee.setJob(job);
        employee.setDepartment(department);
        employee.setManager(manager);

        employeeRepository.save(employee);

        return "redirect:/employee";
    }

    // Eliminar empleado
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeRepository.deleteById(id);
        return "redirect:/employee";
    }
}