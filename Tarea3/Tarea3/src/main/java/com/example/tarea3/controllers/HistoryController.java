package com.example.tarea3.controllers;
import com.example.tarea3.model.Employee;
import com.example.tarea3.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HistoryController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/history")
    public String showEmployeeHistory(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Employee> employees;
        if (keyword != null && !keyword.isEmpty()) {
            employees = employeeRepository.searchByFilter(keyword);
        } else {
            employees = employeeRepository.findAll();
        }

        model.addAttribute("employees", employees);
        model.addAttribute("keyword", keyword);
        return "history/list";
    }
}
