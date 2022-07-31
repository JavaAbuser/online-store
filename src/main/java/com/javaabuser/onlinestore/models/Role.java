package com.javaabuser.onlinestore.models;

import com.javaabuser.onlinestore.models.enums.ERole;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @NotBlank
    @Column(name = "role")
    private ERole role;

    public Role(ERole role) {
        this.role = role;
    }

    public Role() {
    }

    public int getId() {
        return id;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }
}
