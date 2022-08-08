package com.javaabuser.onlinestore.services;

import com.javaabuser.onlinestore.exceptions.CustomerNotFoundException;
import com.javaabuser.onlinestore.models.Customer;
import com.javaabuser.onlinestore.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Customer> findById(int customerId){
        return customerRepository.findById(customerId);
    }

    public Optional<Customer> findByEmail(String customerEmail){
        Optional<Customer> customer = customerRepository.findByEmail(customerEmail);
        if(customer.isEmpty()){
            throw new CustomerNotFoundException();
        }
        return customer;
    }

    public boolean existsCustomerByEmail(String customerEmail){
        return customerRepository.existsCustomerByEmail(customerEmail);
    }

    @Transactional
    public void save(Customer customer){
        
    }
}
