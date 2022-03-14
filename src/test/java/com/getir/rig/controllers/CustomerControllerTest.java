package com.getir.rig.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.rig.entities.Book;
import com.getir.rig.entities.BookOrder;
import com.getir.rig.entities.Customer;
import com.getir.rig.models.CustomerModel;
import com.getir.rig.repositories.CustomerRepository;
import com.getir.rig.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CustomerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final ObjectMapper om = new ObjectMapper();

    @Test
    public void addCustomerPositive() throws Exception {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setEmail("test@test.com");
        customerModel.setName("Test");
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .postForEntity("/v1/customer", customerModel, String.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void addCustomerNegative() throws Exception {
        CustomerModel customerModel = new CustomerModel();
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .postForEntity("/v1/customer", customerModel, String.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getCustomer() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .getForEntity("/v1/customer/1/orders" ,String.class);
        printJSON(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private static void printJSON(Object object) {
        String result;
        try {
            result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }




}