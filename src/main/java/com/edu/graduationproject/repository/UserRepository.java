package com.edu.graduationproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.model.AuthProvider;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public Optional<User> findByEmail(String email);

    @Query("SELECT DISTINCT ur.user FROM UserRole ur WHERE ur.role.id IN ('USER','ADMIN','STAF')")
    public List<User> getAdministrators();

    @Query("SELECT u FROM User u WHERE u.reset_pwd_token = ?1")
    public Optional<User> findByResestPasswordToken(String token);
}
