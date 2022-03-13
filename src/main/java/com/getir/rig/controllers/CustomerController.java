package com.getir.rig.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.rig.entities.BookOrder;
import com.getir.rig.entities.Customer;
import com.getir.rig.entities.Report;
import com.getir.rig.models.CustomerModel;
import com.getir.rig.services.CustomerService;
import com.getir.rig.services.OrderService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.*;

import static com.getir.rig.config.ApplicationConstant.CUSTOMER_PATH;

@RestController
@RequestMapping(CUSTOMER_PATH)
@Slf4j
public class CustomerController extends ErrorController {

    private final CustomerService customerService;

    private final OrderService orderService;

    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody CustomerModel customerModel) {
        log.info("New user creation request for {} ", customerModel.getEmail());
        return new ResponseEntity<>(customerService.addCustomer(customerModel), HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<Page<BookOrder>> getCustomerOrder(@PathVariable("customerId") Long id, @RequestParam(value = "after", required = false) Date after, @RequestParam(value = "before", required = false) Date before, final Pageable pageable) {
        if (Objects.nonNull(after) && Objects.nonNull(before)) {
            log.info("Fetching customer orders for {} between ({}, {})", id, after, before);
            return new ResponseEntity<>(orderService.getOrdersBetweenDates(id, after, before, pageable), HttpStatus.OK);
        }
        log.info("Fetching customer orders for {} ", id);
        return new ResponseEntity<>(orderService.getOrdersForCustomerId(id, pageable), HttpStatus.OK);
    }

    @GetMapping("/{customerId}/stats")
    public ResponseEntity<List<Report>> getCustomerStats(@PathVariable("customerId") Long id) {
        log.info("Fetching stats for {}", id);
        return new ResponseEntity<>(orderService.getStatsReport(id), HttpStatus.OK);
    }


}
