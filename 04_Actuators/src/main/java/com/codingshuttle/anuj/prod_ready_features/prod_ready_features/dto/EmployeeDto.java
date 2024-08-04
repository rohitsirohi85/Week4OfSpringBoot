package com.codingshuttle.anuj.prod_ready_features.prod_ready_features.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {

    
    private Long id;

    private String name;

    private String email;

    private Integer age;
    private String isActive;

    private String role;

    private Integer prime;

    private double salary;

    private LocalDate dateOfJoining;

}
