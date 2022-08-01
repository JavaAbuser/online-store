package com.javaabuser.onlinestore.controllers;

import com.javaabuser.onlinestore.security.jwt.JwtUtil;
import com.javaabuser.onlinestore.services.CustomerService;
import com.javaabuser.onlinestore.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class AuthController {
    private final CustomerService customerService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(CustomerService customerService, RoleService roleService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.customerService = customerService;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> authenticate(){

    }

    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout(){

    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> register(){

    }
}
