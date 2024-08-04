package com.gcu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcu.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
}