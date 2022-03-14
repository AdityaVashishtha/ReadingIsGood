package com.getir.rig.controllers;

import com.getir.rig.entities.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BookControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void addBookTestPositive() throws Exception {
        Book book = new Book();
        book.setTitle("Dark kNight");
        book.setIsbn("IS98890-0899");
        book.setDescription("It's About batman");
        book.setCost(5.50);
        book.setQuantity(10);
        book.setPublisher("DC");
        book.setYear(2000);
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .postForEntity("/v1/book", book, String.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void addBookNegative() throws Exception {
        Book book = new Book();
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .postForEntity("/v1/book", book, String.class);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }


}