package com.getir.rig.models;

import com.getir.rig.entities.Customer;
import lombok.Data;

@Data
public class CustomerModel {
    private String email;
    private String name;

    public Customer toCustomerEntity() {
        Customer customer = new Customer();
        customer.setEmail(this.email);
        customer.setName(this.name);
        return customer;
    }
}
