package com.javaabuser.onlinestore.services;

import com.javaabuser.onlinestore.models.Customer;
import com.javaabuser.onlinestore.repositories.CustomerRepository;
import com.javaabuser.onlinestore.security.CustomerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findByName(username);
        if(customer.isEmpty()){
            throw new UsernameNotFoundException("Customer not found.");
        }
        return new CustomerDetails(customer.get());
    }
}
