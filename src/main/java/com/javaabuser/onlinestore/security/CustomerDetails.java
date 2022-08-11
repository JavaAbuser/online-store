package com.javaabuser.onlinestore.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javaabuser.onlinestore.models.Customer;
import com.javaabuser.onlinestore.models.Role;
import com.javaabuser.onlinestore.models.enums.ERole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class CustomerDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private final Customer customer;
    private int id;
    private String name;
    @JsonIgnore
    private String password;
    private String email;
    private Role role;

    public CustomerDetails (Customer customer) {
        this.customer = customer;
        this.id = customer.getId();
        this.name = customer.getName();
        this.password = customer.getPassword();
        this.email = customer.getEmail();
        this.role = new Role(ERole.CUSTOMER);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role.getRole().name()));

        return authorities;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
