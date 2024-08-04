package com.SpringDataMapping.OneToOne_Mapping.entities;

import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="departments")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long depart_Id;

  @Column(nullable = false)
   private String title;

   @OneToOne
   @JoinColumn(name = "department's_manager")
   private EmployeeEntity manager;

   @OneToMany(mappedBy = "workerDepartment" , fetch = FetchType.LAZY)  // bcz one department can have many workers so at department perspective it's one to many
  
   private Set<EmployeeEntity> workers;

   @ManyToMany(mappedBy = "freelanceDepartment")
   private Set<EmployeeEntity> freelancers;
}
