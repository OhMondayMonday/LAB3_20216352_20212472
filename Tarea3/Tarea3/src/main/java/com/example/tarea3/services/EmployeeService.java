package com.example.tarea3.services;

import com.example.tarea3.dto.EmployeeResponseDTO;
import com.example.tarea3.models.Employee;
import com.example.tarea3.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Page<Employee> findAllPaginated(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
            Sort.by(sortBy).descending() : 
            Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeRepository.findAll(pageable);
    }

    public List<Employee> searchByFilter(String filter) {
        if (filter == null || filter.trim().isEmpty()) {
            return employeeRepository.findAll();
        }
        return employeeRepository.searchByFilter(filter);
    }

    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee) {
        validateEmployee(employee);
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee) {
        if (employee.getEmployeeId() == null) {
            throw new IllegalArgumentException("El ID del empleado no puede ser nulo para actualizar");
        }
        
        if (!employeeRepository.existsById(employee.getEmployeeId())) {
            throw new IllegalArgumentException("El empleado con ID " + employee.getEmployeeId() + " no existe");
        }
        
        validateEmployee(employee);
        return employeeRepository.save(employee);
    }

    public void deleteById(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalArgumentException("El empleado con ID " + id + " no existe");
        }
        
        // Verificar si el empleado es manager de otros empleados
        List<Employee> subordinates = employeeRepository.findByManagerEmployeeId(id);
        if (!subordinates.isEmpty()) {
            throw new IllegalStateException("No se puede eliminar el empleado porque es manager de otros empleados");
        }
        
        employeeRepository.deleteById(id);
    }

    public List<Employee> findAllManagers() {
        return employeeRepository.findAllManagers();
    }

    public List<Employee> findBySalaryGreaterThan(double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }

    public boolean existsById(Integer id) {
        return employeeRepository.existsById(id);
    }

    public long count() {
        return employeeRepository.count();
    }

    public EmployeeResponseDTO getEmployeeStatistics() {
        long totalEmployees = employeeRepository.count();
        List<Employee> highSalaryEmployees = employeeRepository.findBySalaryGreaterThan(15000);
        List<Employee> managers = employeeRepository.findAllManagers();
        
        return new EmployeeResponseDTO(totalEmployees, highSalaryEmployees.size(), managers.size());
    }

    private void validateEmployee(Employee employee) {
        if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del empleado es obligatorio");
        }
        
        if (employee.getLastName() == null || employee.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del empleado es obligatorio");
        }
        
        if (employee.getEmail() == null || employee.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email del empleado es obligatorio");
        }
        
        if (!isValidEmail(employee.getEmail())) {
            throw new IllegalArgumentException("El formato del email no es válido");
        }
        
        if (employee.getHireDate() == null) {
            throw new IllegalArgumentException("La fecha de contratación es obligatoria");
        }
        
        if (employee.getSalary() != null && employee.getSalary().doubleValue() < 0) {
            throw new IllegalArgumentException("El salario no puede ser negativo");
        }
    }

    private boolean isValidEmail(String email) {
        // Enhanced email validation with regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
