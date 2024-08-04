package com.codingshuttle.anuj.prod_ready_features.prod_ready_features;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.clients.EmployeeClient;
import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.dto.EmployeeDto;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdReadyFeaturesApplicationTests {
 
    @Autowired
    private EmployeeClient employeeClient;

	@Test
    void getAllEmployees(){
  List<EmployeeDto> employeeDto = employeeClient.getAllEmployees();
  System.out.println(employeeDto);
    }



    @Test
    void getEmployeeById(){
      EmployeeDto employeeDto=employeeClient.getEmployeeById(1L);  // L for long
      System.out.println(employeeDto);
    }


    @Test
    void createNewEmployee(){
      EmployeeDto employeeDto = new EmployeeDto(null , "rohit" , "rohit1012@gmail.com", null, null, null, null, 0, null);
     EmployeeDto savedEmployeeDto = employeeClient.createNewEmployee(employeeDto);
     System.out.println(savedEmployeeDto);
    }

}
