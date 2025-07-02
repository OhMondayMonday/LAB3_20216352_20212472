package com.example.tarea3.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class JobHistoryId implements Serializable {

    private Integer employeeId;
    private LocalDateTime startDate;

    public JobHistoryId() {}

    public JobHistoryId(Integer employeeId, LocalDateTime startDate) {
        this.employeeId = employeeId;
        this.startDate = startDate;
    }

    // Getters y Setters
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobHistoryId that = (JobHistoryId) o;
        return Objects.equals(employeeId, that.employeeId) &&
               Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, startDate);
    }

    @Override
    public String toString() {
        return "JobHistoryId{" +
                "employeeId=" + employeeId +
                ", startDate=" + startDate +
                '}';
    }
}

