package com.javaabuser.onlinestore.models;

import com.javaabuser.onlinestore.models.enums.ERole;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Size(max = 30)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "password")
    private String password;

    @Email
    @NotNull
    @Size(max = 40)
    @Column(name = "email")
    private String email;

    @Transient
    private Role role;

    public Customer() {
    }

    public Customer(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = new Role(ERole.CUSTOMER);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return id == customer.id && name.equals(customer.name) && password.equals(customer.password) && email.equals(customer.email) && role.equals(customer.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, email, role);
    }
}
