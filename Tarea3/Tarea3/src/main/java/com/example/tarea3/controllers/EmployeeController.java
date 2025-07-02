package com.example.tarea3.controllers;

import com.example.tarea3.models.Department;
import com.example.tarea3.models.Employee;
import com.example.tarea3.models.Job;
import com.example.tarea3.repositories.DepartmentRepository;
import com.example.tarea3.repositories.JobRepository;
import com.example.tarea3.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public String listEmployees(
            @RequestParam(name = "filter", required = false) String filter,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "employeeId") String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
            Model model) {
        
        try {
            if (filter != null && !filter.isEmpty()) {
                List<Employee> list = employeeService.searchByFilter(filter);
                model.addAttribute("employeeList", list);
                model.addAttribute("filter", filter);
                model.addAttribute("isPaginated", false);
            } else {
                Page<Employee> employeePage = employeeService.findAllPaginated(page, size, sortBy, sortDir);
                model.addAttribute("employeePage", employeePage);
                model.addAttribute("employeeList", employeePage.getContent());
                model.addAttribute("isPaginated", true);
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", employeePage.getTotalPages());
                model.addAttribute("sortBy", sortBy);
                model.addAttribute("sortDir", sortDir);
            }
            return "employees/employee-list";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar la lista de empleados: " + e.getMessage());
            return "error/general-error";
        }
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        try {
            model.addAttribute("employee", new Employee());
            model.addAttribute("managers", employeeService.findAllManagers());
            model.addAttribute("departments", departmentRepository.findAll());
            model.addAttribute("jobs", jobRepository.findAll());
            return "employees/employee-form";
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar el formulario: " + e.getMessage());
            return "error/general-error";
        }
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute Employee employee, 
                              BindingResult result, 
                              RedirectAttributes redirectAttributes, 
                              Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("managers", employeeService.findAllManagers());
                model.addAttribute("departments", departmentRepository.findAll());
                model.addAttribute("jobs", jobRepository.findAll());
                return "employees/employee-form";
            }
            
            employeeService.save(employee);
            redirectAttributes.addFlashAttribute("success", "Empleado creado exitosamente");
            return "redirect:/employees";
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear empleado: " + e.getMessage());
            model.addAttribute("managers", employeeService.findAllManagers());
            model.addAttribute("departments", departmentRepository.findAll());
            model.addAttribute("jobs", jobRepository.findAll());
            return "employees/employee-form";
        }
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Employee employee = employeeService.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + id));

            model.addAttribute("employee", employee);
            model.addAttribute("jobs", jobRepository.findAll());
            model.addAttribute("departments", departmentRepository.findAll());
            model.addAttribute("managers", employeeService.findAllManagers());
            model.addAttribute("isEdit", true);

            return "employees/employee-form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al cargar empleado: " + e.getMessage());
            return "redirect:/employees";
        }
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee,
                                 @RequestParam(required = false) String jobId,
                                 @RequestParam(required = false) Integer departmentId,
                                 @RequestParam(required = false) Integer managerId,
                                 RedirectAttributes redirectAttributes) {
        try {
            if (jobId != null && !jobId.isEmpty()) {
                Job job = jobRepository.findById(jobId).orElse(null);
                employee.setJob(job);
            }
            
            if (departmentId != null) {
                Department department = departmentRepository.findById(departmentId).orElse(null);
                employee.setDepartment(department);
            }
            
            if (managerId != null) {
                Employee manager = employeeService.findById(managerId).orElse(null);
                employee.setManager(manager);
            }

            employeeService.update(employee);
            redirectAttributes.addFlashAttribute("success", "Empleado actualizado exitosamente");
            return "redirect:/employees";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar empleado: " + e.getMessage());
            return "redirect:/employees/edit/" + employee.getEmployeeId();
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            employeeService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Empleado eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar empleado: " + e.getMessage());
        }
        return "redirect:/employees";
    }

    @GetMapping("/view/{id}")
    public String viewEmployee(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Employee employee = employeeService.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + id));
            
            model.addAttribute("employee", employee);
            return "employees/employee-detail";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al cargar empleado: " + e.getMessage());
            return "redirect:/employees";
        }
    }
}