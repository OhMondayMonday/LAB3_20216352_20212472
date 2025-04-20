package com.example.tarea3.repositories;

import com.example.tarea3.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {
}
