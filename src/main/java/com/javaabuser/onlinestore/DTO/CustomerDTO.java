package com.javaabuser.onlinestore.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class CustomerLoginDTO {
    @NotNull
    private String password;

    @Email
    @NotNull
    @Max(value = 40)
    private String email;

    public CustomerLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public CustomerLoginDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
