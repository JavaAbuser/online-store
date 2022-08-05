package com.javaabuser.onlinestore.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class RegisterRequest {
    @NotNull
    @Max(value = 30)
    private String name;

    @NotNull
    private String password;

    @Email
    @NotNull
    @Max(value = 40)
    private String email;

    public RegisterRequest(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public RegisterRequest() {
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
