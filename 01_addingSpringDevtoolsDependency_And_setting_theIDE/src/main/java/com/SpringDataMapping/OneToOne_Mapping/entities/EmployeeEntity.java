package com.SpringDataMapping.OneToOne_Mapping.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
   private String name;

   @OneToOne(mappedBy = "manager")
   @JsonIgnore
   private DepartmentEntity managedDepartment;

   @ManyToOne(cascade = CascadeType.ALL)                // bcz many employees can work in a single department so from employee perspective manyToOne
  //  @JoinColumn(name = "worker_department_id" , referencedColumnName = "id") // for making column
  @JoinTable(name = "worker_department_mapping")  // crating another table 
   @JsonIgnore
   private DepartmentEntity workerDepartment;

   @ManyToMany
   @JoinTable(name = "freelancer_department_mapping" , joinColumns = @JoinColumn(name="employee_id")
                               , inverseJoinColumns = @JoinColumn(name="department_id") )
    @JsonIgnore
   private Set<DepartmentEntity> freelanceDepartment;
}