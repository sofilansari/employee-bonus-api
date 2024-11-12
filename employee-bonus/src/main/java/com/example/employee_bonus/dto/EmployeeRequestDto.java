package com.example.employee_bonus.dto;

import java.util.List;

import lombok.Data;


@Data
public class EmployeeRequestDto {
	private String errorMessage;
    private List<CurrencyEmployeesDto> data;

}
