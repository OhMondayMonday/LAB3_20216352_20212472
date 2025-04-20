package com.example.tarea3.services;

import com.example.tarea3.repositories.EmpleadoVistaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpleadoVistaService {
    private final EmpleadoVistaRepository repo;

    public EmpleadoVistaService(EmpleadoVistaRepository repo) {
        this.repo = repo;
    }

    public List<EmpleadoVista> listarTodo() {
        return repo.findAll();
    }
}
