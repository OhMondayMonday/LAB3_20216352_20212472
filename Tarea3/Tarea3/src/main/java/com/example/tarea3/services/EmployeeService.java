package com.example.tarea3.services;

import com.example.tarea3.models.Employee;
import com.example.tarea3.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> listar(String filtro) {
        if (filtro == null || filtro.isEmpty()) {
            return repo.findAll();
        }
        return repo.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCaseOrPuestoContainingIgnoreCase(
                filtro, filtro, filtro);
    }

    public void guardar(Employee e) {
        repo.save(e);
    }

    public Employee buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
