package com.example.employee_bonus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee_bonus.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
