package com.getir.rig.models;

import com.getir.rig.entities.Customer;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@ToString
public class CustomerModel {

    @NotBlank(message = "Email Can't be blank")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Name should not be blank")
    private String name;

    public Customer toCustomerEntity() {
        Customer customer = new Customer();
        customer.setEmail(this.email);
        customer.setName(this.name);
        return customer;
    }
}
