package com.codingshuttle.anuj.prod_ready_features.prod_ready_features.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;

import org.springframework.http.HttpHeaders; // Import HttpHeaders to use CONTENT_TYPE and APPLICATION_JSON constants
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;

@Configuration
public class RestClientConfig {

    @Value("${employeeService.base.url}")
    private String BASE_URL;

    @Bean
    @Qualifier("employeeRestClient")
    public RestClient getEmployeeServiceRestClient() {
        // Use HttpHeaders constants for content type and application/json
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, (req, res) -> {
                    System.out.println(new String(res.getBody().readAllBytes())); // it give the server error 
                    throw new ResourceNotFoundException("could not connect to server");
                })
                .build();
    }
}
