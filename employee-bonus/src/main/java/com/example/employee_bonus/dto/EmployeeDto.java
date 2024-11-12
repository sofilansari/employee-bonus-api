package com.example.employee_bonus.dto;

import java.time.LocalDate;

import com.example.employee_bonus.model.Department;
import com.example.employee_bonus.model.Employee;

import lombok.Data;

@Data
public class EmployeeDto {
	
	 private Long id;
	    private String empName;
	    private Double amount;
	    private String currency;
	    private LocalDate joiningDate;
	    private LocalDate exitDate;
	    private String departmentName;

	    public static EmployeeDto toDto(Employee employee) {
	        EmployeeDto dto = new EmployeeDto();
	        dto.setId(employee.getId());
	        dto.setEmpName(employee.getEmpName());
	        dto.setAmount(employee.getAmount());
	        dto.setCurrency(employee.getCurrency());
	        dto.setJoiningDate(employee.getJoiningDate());
	        dto.setExitDate(employee.getExitDate());
	        if (employee.getDepartment() != null) {
	            dto.setDepartmentName(employee.getDepartment().getName());
	        }
	        return dto;
	    }

	    public Employee toEntity(Department department) {
	        Employee employee = new Employee();
	        employee.setId(this.id);
	        employee.setEmpName(this.empName);
	        employee.setAmount(this.amount);
	        employee.setCurrency(this.currency);
	        employee.setJoiningDate(this.joiningDate);
	        employee.setExitDate(this.exitDate);
	        employee.setDepartment(department);
	        return employee;
	    }
}
