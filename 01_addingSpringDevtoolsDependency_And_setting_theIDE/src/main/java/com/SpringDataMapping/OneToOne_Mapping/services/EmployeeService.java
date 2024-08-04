package com.SpringDataMapping.OneToOne_Mapping.services;

import org.springframework.stereotype.Service;

import com.SpringDataMapping.OneToOne_Mapping.entities.EmployeeEntity;
import com.SpringDataMapping.OneToOne_Mapping.repo.EmployeeRepo;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public EmployeeEntity getEmployeeById(Long id) {
     return employeeRepo.findById(id).orElse(null);
    }

    public EmployeeEntity createNewEmployee(EmployeeEntity EmployeeEntity) {
        return employeeRepo.save(EmployeeEntity);
    }

    
    
}