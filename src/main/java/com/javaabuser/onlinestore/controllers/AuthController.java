package com.javaabuser.onlinestore.controllers;

import com.javaabuser.onlinestore.models.Customer;
import com.javaabuser.onlinestore.models.Role;
import com.javaabuser.onlinestore.models.enums.ERole;
import com.javaabuser.onlinestore.security.CustomerDetails;
import com.javaabuser.onlinestore.security.jwt.JwtUtil;
import com.javaabuser.onlinestore.services.CustomerService;
import com.javaabuser.onlinestore.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    public ResponseEntity<Customer> authenticate(@RequestBody @Valid Customer customer){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(customer.getName(), customer.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomerDetails customerDetails = (CustomerDetails) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtil.generateJwtCookie(customerDetails);
        Set<String> rolesString = customerDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        Set<Role> rolesRole = new HashSet<>();
        for(String role : rolesString){
            switch (role){
                case "CUSTOMER":
                    rolesRole.add(new Role(ERole.CUSTOMER));
                    break;
                case "SELLER":
                    rolesRole.add(new Role(ERole.SELLER));
                    break;
                case "MODERATOR":
                    rolesRole.add(new Role(ERole.MODERATOR));
                    break;
                case "OWNER":
                    rolesRole.add(new Role(ERole.OWNER));
                    break;
            }
        }

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new Customer(customerDetails.getName(),
                        customerDetails.getPassword(),
                        customerDetails.getEmail(),
                        rolesRole));
    }

    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout(){

    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> register(){
        
    }
}
