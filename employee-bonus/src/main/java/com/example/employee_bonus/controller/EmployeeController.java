package com.example.employee_bonus.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_bonus.dto.CurrencyEmployeesDto;
import com.example.employee_bonus.dto.EmployeeDto;
import com.example.employee_bonus.dto.EmployeeRequestDto;
import com.example.employee_bonus.service.EmployeeService;

@RestController
@RequestMapping("/tci")
public class EmployeeController {
	
	
    private final  EmployeeService employeeService;
    
    public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}


	@PostMapping("/employee-bonus")
    public ResponseEntity<Void> saveEmployees(@RequestBody Map<String, List<EmployeeDto>> payload) {
        List<EmployeeDto> employeeDtos = payload.get("employees");
        employeeService.saveEmployees(employeeDtos);
        return ResponseEntity.ok().build();
    }
    
    
    @GetMapping("/employee-bonus")
    public ResponseEntity<EmployeeRequestDto> getEligibleEmployees(
            @RequestParam("date") @DateTimeFormat(pattern = "MMM-dd-yyyy") LocalDate date) {
        
        List<CurrencyEmployeesDto> eligibleEmployeesData = employeeService.getEligibleEmployees(date);
        
        EmployeeRequestDto dto = new EmployeeRequestDto();
        dto.setErrorMessage("");
        dto.setData(eligibleEmployeesData);

        return ResponseEntity.ok(dto);
    }
}
