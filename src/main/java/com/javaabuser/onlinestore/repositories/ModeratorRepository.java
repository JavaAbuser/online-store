package com.javaabuser.onlinestore.repositories;

import com.javaabuser.onlinestore.models.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Integer> {
}
