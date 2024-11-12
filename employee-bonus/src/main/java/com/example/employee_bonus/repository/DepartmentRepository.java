package com.example.employee_bonus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee_bonus.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
	Department findByName(String name);

}
