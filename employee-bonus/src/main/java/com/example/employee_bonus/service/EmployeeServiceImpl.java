package com.example.employee_bonus.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.example.employee_bonus.dto.CurrencyEmployeesDto;
import com.example.employee_bonus.dto.EmployeeDto;
import com.example.employee_bonus.dto.EmployeeSummaryDto;
import com.example.employee_bonus.model.Department;
import com.example.employee_bonus.model.Employee;
import com.example.employee_bonus.repository.DepartmentRepository;
import com.example.employee_bonus.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	
    private final EmployeeRepository employeeRepository;

    private final  DepartmentRepository departmentRepository;
    
    
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
		this.employeeRepository = employeeRepository;
		this.departmentRepository = departmentRepository;
	}

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
        
        Map<String, List<EmployeeSummaryDto>> employeesByCurrency = employees.stream()
                .filter(e -> (e.getJoiningDate().isBefore(date) || e.getJoiningDate().isEqual(date)) &&
                             (e.getExitDate() == null || e.getExitDate().isAfter(date)))
                .collect(Collectors.groupingBy(Employee::getCurrency,
                        Collectors.mapping(e -> {
                            EmployeeSummaryDto summaryDto = new EmployeeSummaryDto();
                            summaryDto.setEmpName(e.getEmpName());
                            summaryDto.setAmount(e.getAmount());
                            return summaryDto;
                        }, Collectors.toList())
                ));

        return employeesByCurrency.entrySet().stream()
                .map(entry -> {
                    CurrencyEmployeesDto currencyEmployees = new CurrencyEmployeesDto();
                    currencyEmployees.setCurrency(entry.getKey());
                    currencyEmployees.setEmployees(entry.getValue());
                    return currencyEmployees;
                })
                .collect(Collectors.toList());
    }

}
