package com.SpringDataMapping.OneToOne_Mapping.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.SpringDataMapping.OneToOne_Mapping.entities.DepartmentEntity;
import com.SpringDataMapping.OneToOne_Mapping.entities.EmployeeEntity;
import com.SpringDataMapping.OneToOne_Mapping.repo.DepartmentRepo;
import com.SpringDataMapping.OneToOne_Mapping.repo.EmployeeRepo;

@Service
public class DepartmentService {
   private final DepartmentRepo departmentRepo;
   private final EmployeeRepo employeeRepo;

   public DepartmentService(DepartmentRepo departmentRepo, EmployeeRepo employeeRepo) {
      this.departmentRepo = departmentRepo;
      this.employeeRepo = employeeRepo;
   }

   public DepartmentEntity getDepartmentById(Long id) {
      return departmentRepo.findById(id).orElse(null);
   }

   public DepartmentEntity createNewDepartment(DepartmentEntity departmentEntity) {
      return departmentRepo.save(departmentEntity);
   }

   public DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId) {
      Optional<DepartmentEntity> departmentEntity = departmentRepo.findById(departmentId);
      Optional<EmployeeEntity> employeeEntity = employeeRepo.findById(employeeId);

      return departmentEntity.flatMap(department -> employeeEntity.map(employee -> {
         department.setManager(employee);
         return departmentRepo.save(department);
      })).orElse(null);
   }

   public DepartmentEntity getAssignedDepartmentOfManager(Long employeeId) {

      // Optional<EmployeeEntity> employeeEntity=employeeRepo.findById(employeeId);
      // return employeeEntity.map(employee->
      // employee.getManagedDepartment()).orElse(null);

      /* another way to get */

      EmployeeEntity employeeEntity = EmployeeEntity.builder().id(employeeId).build();
      return departmentRepo.findByManager(employeeEntity); // make this method in departmentRepo
   }

   public DepartmentEntity assignWorkerToDepartment(Long departmentId, Long employeeId) {
      Optional<DepartmentEntity> departmentEntity = departmentRepo.findById(departmentId);
      Optional<EmployeeEntity> employeeEntity = employeeRepo.findById(employeeId);

      return departmentEntity.flatMap(department -> employeeEntity.map(employee -> {
         employee.setWorkerDepartment(department);
         employeeRepo.save(employee);
         department.getWorkers().add(employee);
         return department;
      })).orElse(null);
   }

   public DepartmentEntity assignFreelancerToDepartment(Long departmentId, Long employeeId) {
      Optional<DepartmentEntity> departmentEntity = departmentRepo.findById(departmentId);
      Optional<EmployeeEntity> employeeEntity = employeeRepo.findById(employeeId);

      return departmentEntity.flatMap(department -> employeeEntity.map(employee -> {
         
         employee.getFreelanceDepartment().add(department);
         employeeRepo.save(employee);
         department.getFreelancers().add(employee);
         
         return department;
      })).orElse(null);
   }

}
