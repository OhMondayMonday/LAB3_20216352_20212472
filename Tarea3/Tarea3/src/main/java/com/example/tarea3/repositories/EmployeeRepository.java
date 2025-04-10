package com.example.tarea3.repositories;

import com.example.tarea3.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCaseOrPuestoContainingIgnoreCaseOrDepartamentoContainingIgnoreCaseOrCiudadContainingIgnoreCase(
            String nombre, String apellido, String puesto, String departamento, String ciudad);
}
