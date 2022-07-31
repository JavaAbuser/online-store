package com.javaabuser.onlinestore.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javaabuser.onlinestore.models.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private final Customer customer;
    private int id;
    private String name;
    @JsonIgnore
    private String password;
    private String email;
    private List<? extends GrantedAuthority> authorities;

    public CustomerDetails (Customer customer) {
        this.customer = customer;
        this.id = customer.getId();
        this.name = customer.getName();
        this.password = customer.getPassword();
        this.email = customer.getEmail();
        this.authorities = customer.getRoles().stream().map(
                role -> new SimpleGrantedAuthority(role.getRole().name())
        ).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
}
