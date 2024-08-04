package com.SpringDataMapping.OneToOne_Mapping.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringDataMapping.OneToOne_Mapping.entities.DepartmentEntity;
import com.SpringDataMapping.OneToOne_Mapping.entities.EmployeeEntity;

@Repository
public interface DepartmentRepo extends JpaRepository<DepartmentEntity , Long> {

    DepartmentEntity findByManager(EmployeeEntity employeeEntity);
    
}
