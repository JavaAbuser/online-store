package com.javaabuser.onlinestore.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterRequest {
    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    private String password;

    @Email
    @NotNull
    @Size(max = 40)
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
