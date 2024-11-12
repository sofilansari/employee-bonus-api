package com.example.employee_bonus.dto;

import com.example.employee_bonus.model.Department;

import lombok.Data;

@Data
public class DepartmentDto {
	private Long id;
	private String name;
	
	
	public static DepartmentDto toDto(Department department) {
		
		DepartmentDto dto=new DepartmentDto();
		dto.setId(department.getId());
		dto.setName(department.getName());
		
		return dto;
		
	}

}
