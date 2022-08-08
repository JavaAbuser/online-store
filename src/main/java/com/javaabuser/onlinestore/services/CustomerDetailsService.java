package com.javaabuser.onlinestore.services;

import com.javaabuser.onlinestore.models.Customer;
import com.javaabuser.onlinestore.repositories.CustomerRepository;
import com.javaabuser.onlinestore.security.CustomerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found customer with name " + username));

        return new CustomerDetails(customer);
    }
}
