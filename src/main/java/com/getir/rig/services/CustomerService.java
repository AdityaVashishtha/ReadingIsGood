package com.getir.rig.services;

import com.getir.rig.entities.Customer;
import com.getir.rig.models.CustomerModel;
import com.getir.rig.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(CustomerModel customerModel) {
        Customer customer = customerModel.toCustomerEntity();
        return customerRepository.save(customer);
    }

}
