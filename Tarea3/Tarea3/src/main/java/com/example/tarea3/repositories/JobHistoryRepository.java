package com.example.tarea3.repositories;

import com.example.tarea3.models.JobHistory;
import com.example.tarea3.models.JobHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, JobHistoryId> {

    // Buscar historial por ID de empleado
    List<JobHistory> findByEmployeeIdOrderByStartDateDesc(Integer employeeId);

    // Buscar historial por job_id
    List<JobHistory> findByJobIdOrderByStartDateDesc(String jobId);

    // Buscar historial por department_id
    List<JobHistory> findByDepartmentIdOrderByStartDateDesc(Integer departmentId);

    // Buscar historial en un rango de fechas
    @Query("SELECT jh FROM JobHistory jh WHERE jh.startDate >= :startDate AND jh.endDate <= :endDate")
    List<JobHistory> findByDateRange(@Param("startDate") LocalDateTime startDate, 
                                   @Param("endDate") LocalDateTime endDate);

    // Buscar empleados que trabajaron en un job específico
    @Query("SELECT DISTINCT jh.employeeId FROM JobHistory jh WHERE jh.jobId = :jobId")
    List<Integer> findEmployeeIdsByJobId(@Param("jobId") String jobId);

    // Contar cuántos empleados trabajaron en un job
    @Query("SELECT COUNT(DISTINCT jh.employeeId) FROM JobHistory jh WHERE jh.jobId = :jobId")
    Long countEmployeesByJobId(@Param("jobId") String jobId);

    // Buscar historial con información completa (con joins)
    @Query("SELECT jh FROM JobHistory jh " +
           "LEFT JOIN FETCH jh.employee e " +
           "LEFT JOIN FETCH jh.job j " +
           "LEFT JOIN FETCH jh.department d " +
           "WHERE jh.employeeId = :employeeId " +
           "ORDER BY jh.startDate DESC")
    List<JobHistory> findByEmployeeIdWithDetails(@Param("employeeId") Integer employeeId);
}