package com.example.employee_bonus.dto;



import java.util.List;
import lombok.Data;

@Data
public class CurrencyEmployeesDto {
    private String currency;
    private List<EmployeeDto> employees;
}

