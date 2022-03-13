package com.getir.rig.services;

import com.getir.rig.entities.Customer;
import com.getir.rig.models.CustomerModel;
import com.getir.rig.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer addCustomer(CustomerModel customerModel) {
        Customer customer = customerModel.toCustomerEntity();
        customerRepository.saveAndFlush(customer);
        return customerRepository.findCustomerByEmail(customerModel.getEmail());
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

}
