package com.codingshuttle.anuj.prod_ready_features.prod_ready_features.clients.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.advice.ApiResponse;
import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.clients.EmployeeClient;
import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.dto.EmployeeDto;
import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class EmployeeClientImpl implements EmployeeClient {

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    private final RestClient restClient;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        log.trace("trying to get list of employees");

        // log.info("info");
        // log.warn("warn");
        // log.debug("debug"); // not a good practice to declare here we use them in
        // cases we want
        // log.error("error");
        // log.trace("trace");

        try {
            ApiResponse<List<EmployeeDto>> employeeDto = restClient.get()
                    .uri("Employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes())); // it run when error occurred like if you
                                                                             // give wrong field or condition
                        throw new ResourceNotFoundException("could not create employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("successfully retrieved employees");
            log.trace("retrieved employee list in getAllEmployees : {}", employeeDto.getData());
            return employeeDto.getData();
        } catch (Exception e) {
            log.error("error occurred in getAllEmployees()"); // give the error
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        log.trace("trying to get employee by id:", employeeId);
        try {
            ApiResponse<EmployeeDto> employeeDto = restClient.get()
                    .uri("Employees/{employeeId}", employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes())); // it run when error occurred like if you
                                                                             // give wrong field or condition
                        throw new ResourceNotFoundException("could not create employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDto.getData();
        } catch (Exception e) {
            log.error("error occurred in getEmployeeBId()");
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        log.trace("trying to create a new employee with info:{}", employeeDto);
        try {
            ApiResponse<EmployeeDto> emResponseCreate = restClient.post()
                    .uri("Employees")
                    .body(employeeDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.debug("4xx client error in creating new employee");
                        log.error(new String(res.getBody().readAllBytes())); // it run when error occurred like if you
                                                                             // give wrong field or condition
                        throw new ResourceNotFoundException("could not create employee");
                    }) // we can give the server error just like that here but we wrote that in config
                       // class so it can be handled globally so reduce code repetition every test
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.trace("successfully crated the employee data:{}", emResponseCreate.getData());
            return emResponseCreate.getData();
        } catch (Exception e) {
            log.error("error occurred in createNewEmployee()");
            throw new RuntimeException(e);
        }
    }

}
