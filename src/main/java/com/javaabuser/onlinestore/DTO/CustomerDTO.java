package com.javaabuser.onlinestore.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import com.javaabuser.onlinestore.util.View;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class CustomerDTO {
    @JsonView(View.ViewForRegistration.class)
    @NotNull
    @Max(value = 30)
    private String name;

    @JsonView(View.ViewForLogin.class)
    @NotNull
    private String password;

    @JsonView(View.ViewForLogin.class)
    @Email
    @NotNull
    @Max(value = 40)
    private String email;

    public CustomerDTO() {
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
}
