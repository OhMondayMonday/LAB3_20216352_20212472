package com.example.tarea3.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model, RedirectAttributes redirectAttributes) {
        logger.warn("IllegalArgumentException: {}", ex.getMessage());
        
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/employees";
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleIllegalStateException(IllegalStateException ex, Model model, RedirectAttributes redirectAttributes) {
        logger.warn("IllegalStateException: {}", ex.getMessage());
        
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/employees";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericException(Exception ex, Model model) {
        logger.error("Unexpected error occurred", ex);
        
        model.addAttribute("error", "Ha ocurrido un error inesperado. Por favor, contacte al administrador del sistema.");
        model.addAttribute("errorDetails", ex.getMessage());
        model.addAttribute("title", "Error del Sistema");
        
        return "error/general-error";
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDataIntegrityViolationException(org.springframework.dao.DataIntegrityViolationException ex, 
                                                       Model model, RedirectAttributes redirectAttributes) {
        logger.warn("Data integrity violation: {}", ex.getMessage());
        
        String userMessage = "No se puede completar la operación debido a restricciones de integridad de datos.";
        
        if (ex.getMessage().contains("foreign key constraint")) {
            userMessage = "No se puede eliminar este registro porque está siendo utilizado por otros elementos del sistema.";
        } else if (ex.getMessage().contains("unique constraint") || ex.getMessage().contains("Duplicate entry")) {
            userMessage = "Ya existe un registro con estos datos. Por favor, verifique la información ingresada.";
        }
        
        redirectAttributes.addFlashAttribute("error", userMessage);
        return "redirect:/employees";
    }
}
