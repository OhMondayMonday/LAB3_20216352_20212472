package com.example.tarea3.services;

import com.example.tarea3.models.JobHistory;
import com.example.tarea3.models.JobHistoryId;
import com.example.tarea3.models.Employee;
import com.example.tarea3.repositories.JobHistoryRepository;
import com.example.tarea3.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JobHistoryService {

    @Autowired
    private JobHistoryRepository jobHistoryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<JobHistory> findAll() {
        return jobHistoryRepository.findAll();
    }

    public Page<JobHistory> findAllPaginated(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
            Sort.by(sortBy).descending() : 
            Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        return jobHistoryRepository.findAll(pageable);
    }

    public List<JobHistory> findByEmployeeId(Integer employeeId) {
        return jobHistoryRepository.findByEmployeeIdOrderByStartDateDesc(employeeId);
    }

    public List<JobHistory> findByEmployeeIdWithDetails(Integer employeeId) {
        return jobHistoryRepository.findByEmployeeIdWithDetails(employeeId);
    }

    public List<JobHistory> findByJobId(String jobId) {
        return jobHistoryRepository.findByJobIdOrderByStartDateDesc(jobId);
    }

    public List<Integer> findEmployeeIdsByJobId(String jobId) {
        return jobHistoryRepository.findEmployeeIdsByJobId(jobId);
    }

    public List<Employee> findEmployeesByJobId(String jobId) {
        List<Integer> employeeIds = jobHistoryRepository.findEmployeeIdsByJobId(jobId);
        return employeeRepository.findAllById(employeeIds);
    }

    public Optional<JobHistory> findById(Integer employeeId, LocalDateTime startDate) {
        JobHistoryId jobHistoryId = new JobHistoryId(employeeId, startDate);
        return jobHistoryRepository.findById(jobHistoryId);
    }

    public JobHistory save(JobHistory jobHistory) {
        validateJobHistory(jobHistory);
        return jobHistoryRepository.save(jobHistory);
    }

    public JobHistory update(JobHistory jobHistory) {
        validateJobHistory(jobHistory);
        
        JobHistoryId id = new JobHistoryId(jobHistory.getEmployeeId(), jobHistory.getStartDate());
        if (!jobHistoryRepository.existsById(id)) {
            throw new IllegalArgumentException("El registro de historial no existe");
        }
        
        return jobHistoryRepository.save(jobHistory);
    }

    public void deleteById(Integer employeeId, LocalDateTime startDate) {
        JobHistoryId jobHistoryId = new JobHistoryId(employeeId, startDate);
        if (!jobHistoryRepository.existsById(jobHistoryId)) {
            throw new IllegalArgumentException("El registro de historial no existe");
        }
        jobHistoryRepository.deleteById(jobHistoryId);
    }

    public boolean existsById(Integer employeeId, LocalDateTime startDate) {
        JobHistoryId jobHistoryId = new JobHistoryId(employeeId, startDate);
        return jobHistoryRepository.existsById(jobHistoryId);
    }

    public long count() {
        return jobHistoryRepository.count();
    }

    public Long countEmployeesByJobId(String jobId) {
        return jobHistoryRepository.countEmployeesByJobId(jobId);
    }

    public List<JobHistory> findRecentHistory(int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by("startDate").descending());
        return jobHistoryRepository.findAll(pageable).getContent();
    }

    public List<JobHistory> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return jobHistoryRepository.findByDateRange(startDate, endDate);
    }

    private void validateJobHistory(JobHistory jobHistory) {
        if (jobHistory.getEmployeeId() == null) {
            throw new IllegalArgumentException("El ID del empleado es obligatorio para el historial");
        }
        
        if (jobHistory.getJobId() == null) {
            throw new IllegalArgumentException("El ID del trabajo es obligatorio para el historial");
        }
        
        if (jobHistory.getStartDate() == null) {
            throw new IllegalArgumentException("La fecha de inicio es obligatoria");
        }
        
        if (jobHistory.getEndDate() == null) {
            throw new IllegalArgumentException("La fecha de fin es obligatoria");
        }
        
        if (jobHistory.getEndDate().isBefore(jobHistory.getStartDate())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        
        // Verificar que el empleado existe
        if (!employeeRepository.existsById(jobHistory.getEmployeeId())) {
            throw new IllegalArgumentException("El empleado con ID " + jobHistory.getEmployeeId() + " no existe");
        }
    }
}
