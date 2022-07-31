package com.javaabuser.onlinestore.repositories;

import com.javaabuser.onlinestore.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String customerName);
    Boolean existsCustomerByEmail(String customerEmail);
}
