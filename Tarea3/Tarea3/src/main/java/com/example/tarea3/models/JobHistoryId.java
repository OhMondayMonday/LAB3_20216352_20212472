package com.example.tarea3.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class JobHistoryId implements Serializable {

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    public JobHistoryId() {
    }

    public JobHistoryId(Integer employeeId, LocalDateTime startDate) {
        this.employeeId = employeeId;
        this.startDate = startDate;
    }

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
        if (!(o instanceof JobHistoryId that)) return false;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, startDate);
    }
}

