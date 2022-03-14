package com.getir.rig.controllers;

import com.getir.rig.entities.Book;
import com.getir.rig.entities.Customer;
import com.getir.rig.models.CreateOrderModel;
import com.getir.rig.repositories.BookRepository;
import com.getir.rig.repositories.CustomerRepository;
import com.getir.rig.repositories.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class OrderControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void init() {
        Book book = new Book();
        book.setTitle("Dark new kNight");
        book.setIsbn("IS98890-08991");
        book.setDescription("It's About batman");
        book.setCost(5.50);
        book.setQuantity(10);
        book.setPublisher("DC");
        book.setYear(2000);
        bookRepository.saveAndFlush(book);
        Customer customer = new Customer();
        customer.setName("Tester");
        customer.setEmail("tester@gmail.com");
        customerRepository.saveAndFlush(customer);
    }

    @AfterEach
    public void destroy() {
        orderRepository.deleteAll();
        bookRepository.deleteAll();
        customerRepository.deleteAll();
    }


    @Test
    public void createOrder() throws Exception {
        CreateOrderModel order = new CreateOrderModel();
        order.setBookId(bookRepository.findAll().get(0).getId());
        order.setEmail("tester@gmail.com");
        order.setQuantity(1);
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .postForEntity("/v1/order", order, String.class);
        System.out.println(response.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void createOrderNegative() throws Exception {
        CreateOrderModel order = new CreateOrderModel();
        order.setBookId(2L);
        order.setEmail("test@gmail.com");
        order.setQuantity(1);
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .postForEntity("/v1/order", order, String.class);
        System.out.println(response.getBody());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }
}
