package com.example.employee_bonus.service;

import java.time.LocalDate;
import java.util.List;
import com.example.employee_bonus.dto.CurrencyEmployeesDto;
import com.example.employee_bonus.dto.EmployeeDto;

public interface EmployeeService {

	    void saveEmployees(List<EmployeeDto> employeeDtos);
	    List<CurrencyEmployeesDto> getEligibleEmployees(LocalDate date);

}
