package com.javaabuser.onlinestore.repositories;

import com.javaabuser.onlinestore.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT customer.email FROM Customer customer WHERE customer.email = :email")
    Optional<Customer> findByEmail(@Param("email") String customerEmail);
    Boolean existsCustomerByEmail(String customerEmail);
}
