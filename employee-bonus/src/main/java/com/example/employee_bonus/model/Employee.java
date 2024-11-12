package com.example.employee_bonus.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

    private String empName;
    private Double amount;
    private String currency;
    private LocalDate joiningDate;
    private LocalDate exitDate;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
