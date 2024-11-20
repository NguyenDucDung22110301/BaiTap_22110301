package com.example.demo.example.repository;

import com.example.demo.example.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<EntityUser, Integer> {
    boolean existsUserByUserName(String userName);
    Optional<EntityUser> findByUserName(String userName);
}
