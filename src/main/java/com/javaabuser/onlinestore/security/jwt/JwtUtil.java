package com.javaabuser.onlinestore.security.jwt;

import com.javaabuser.onlinestore.exceptions.CustomerNotFoundException;
import com.javaabuser.onlinestore.models.Customer;
import com.javaabuser.onlinestore.security.CustomerDetails;
import com.javaabuser.onlinestore.services.CustomerService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    private final CustomerService customerService;

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration.ms}")
    private int jwtExpirationMs;
    @Value("${jwt.cookie.name}")
    private String jwtCookie;

    @Autowired
    public JwtUtil(CustomerService customerService) {
        this.customerService = customerService;
    }

    public String getJwtFromCookies(HttpServletRequest request){
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if(cookie != null){
            return cookie.getValue();
        } else {
            return null;
        }
    }

    public ResponseCookie generateJwtCookie(CustomerDetails customerPrincipal) {
        String jwt = generateTokenFromCustomerEmail(customerPrincipal.getUsername());
        return ResponseCookie.from(jwtCookie, jwt).path("/").httpOnly(true).build();
    }

    public String generateTokenFromCustomerEmail(String customerEmail) {
        Optional<Customer> customer = customerService.findByEmail(customerEmail);
        if(customer.isEmpty()){
            throw new CustomerNotFoundException();
        }
        return Jwts.builder()
                .setSubject(customerEmail)
                .claim("roles", customer.get().getRoles().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public ResponseCookie getCleanJwtCookie() {
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/").build();
        return cookie;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
