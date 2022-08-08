package com.javaabuser.onlinestore.repositories;

import com.javaabuser.onlinestore.models.Role;
import com.javaabuser.onlinestore.models.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRole(ERole roleName);
}
