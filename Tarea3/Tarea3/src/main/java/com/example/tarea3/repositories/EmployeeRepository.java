package com.example.tarea3.repositories;

import com.example.tarea3.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE " +
            "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
            "LOWER(e.lastName) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
            "LOWER(e.job.jobTitle) LIKE LOWER(CONCAT('%', :filter, '%'))")
    List<Employee> searchByFilter(@Param("filter") String filter);

    @Query("SELECT DISTINCT e FROM Employee e WHERE e.employeeId IN (SELECT DISTINCT emp.manager.employeeId FROM Employee emp WHERE emp.manager IS NOT NULL)")
    List<Employee> findAllManagers();

    List<Employee> findBySalaryGreaterThan(double salario);



}
