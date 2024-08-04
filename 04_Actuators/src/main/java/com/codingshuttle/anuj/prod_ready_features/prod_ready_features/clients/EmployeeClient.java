package com.codingshuttle.anuj.prod_ready_features.prod_ready_features.clients;

import java.util.List;

import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.dto.EmployeeDto;

public interface EmployeeClient {
     
  List<EmployeeDto> getAllEmployees();

 EmployeeDto getEmployeeById(Long employeeId);

 EmployeeDto createNewEmployee(EmployeeDto employeeDto);

}
