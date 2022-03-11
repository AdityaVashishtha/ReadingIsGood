package com.getir.rig.controllers;

import com.getir.rig.entities.Customer;
import com.getir.rig.models.CustomerModel;
import com.getir.rig.services.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer addCustomer(@RequestBody CustomerModel customerModel) {
        return this.customerService.addCustomer(customerModel);
    }

    @GetMapping
    public String hello() {
        return "Customer  Hello";
    }

}
