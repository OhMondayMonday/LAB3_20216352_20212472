package com.example.tarea3.repositories;

import com.example.tarea3.dto.DepartamentoPorCiudadDTO;
import com.example.tarea3.dto.GerenteExperienciaDTO;
import com.example.tarea3.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Query("SELECT new com.example.tarea3.dto.DepartamentoPorCiudadDTO(d.location.countryId, d.location.city, COUNT(d)) " +
            "FROM Department d JOIN d.employees e " +
            "GROUP BY d.location.countryId, d.location.city " +
            "HAVING COUNT(e) > 3")
    List<DepartamentoPorCiudadDTO> departamentosConMasDeTresEmpleados();


    @Query("""
       SELECT new com.example.tarea3.dto.GerenteExperienciaDTO(
           d.departmentName,
           CONCAT(m.firstName, ' ', m.lastName),
           m.salary)
       FROM Department d JOIN d.manager m
       WHERE DATEDIFF(CURRENT_DATE, m.hireDate) > 5 * 365
       """)
    List<GerenteExperienciaDTO> gerentesConExperienciaMayorA5();



}
