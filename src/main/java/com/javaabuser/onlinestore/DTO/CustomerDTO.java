package com.javaabuser.onlinestore.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class CustomerDTO {
    @NotNull
    @Max(value = 30)
    private String name;

    @NotNull
    private String password;

    @Email
    @NotNull
    @Max(value = 40)
    private String email;

    public CustomerDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public CustomerDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
