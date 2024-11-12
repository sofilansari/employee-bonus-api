package com.example.employee_bonus.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee_bonus.dto.CurrencyEmployeesDto;
import com.example.employee_bonus.dto.EmployeeDto;
import com.example.employee_bonus.model.Department;
import com.example.employee_bonus.model.Employee;
import com.example.employee_bonus.repository.DepartmentRepository;
import com.example.employee_bonus.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	

	@Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void saveEmployees(List<EmployeeDto> employeeDtos) {
        for (EmployeeDto employeeDto : employeeDtos) {
            Department department = departmentRepository.findByName(employeeDto.getDepartmentName());
            if (department == null) {
                department = new Department();
                department.setName(employeeDto.getDepartmentName());
                departmentRepository.save(department);
            }
            Employee employee = employeeDto.toEntity(department);
            employeeRepository.save(employee);
        }
    }
    
    @Override
    public List<CurrencyEmployeesDto> getEligibleEmployees(LocalDate date) {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> eligibleEmployees = employees.stream()
                .filter(e -> (e.getJoiningDate().isBefore(date) || e.getJoiningDate().isEqual(date)) &&
                             (e.getExitDate() == null || e.getExitDate().isAfter(date)))
                .map(EmployeeDto::toDto)
                .collect(Collectors.toList());

        return eligibleEmployees.stream()
                .collect(Collectors.groupingBy(EmployeeDto::getCurrency))
                .entrySet().stream()
                .map(entry -> {
                    CurrencyEmployeesDto dto = new CurrencyEmployeesDto();
                    dto.setCurrency(entry.getKey());
                    dto.setEmployees(entry.getValue());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
