package com.javaabuser.onlinestore.services;

import com.javaabuser.onlinestore.models.Role;
import com.javaabuser.onlinestore.models.enums.ERole;
import com.javaabuser.onlinestore.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(ERole roleName){
        Optional<Role> role = roleRepository.findByName(roleName);
        if(role.isEmpty()){
            return null;
        }
        return role.get();
    }
}
