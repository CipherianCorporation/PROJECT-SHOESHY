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

	    @Query("SELECT DISTINCT ur.user FROM UserRole ur WHERE ur.role.id IN ('CUST','DIRE','STAF')")
	    public List<User> getAdministrators();

	    @Query("SELECT o FROM User o WHERE verify_code=?1")
	    public User findByVerifyCode(String code);

	    // Derived Query - for checking if User exist by email
	    public boolean existsUserByEmail(String email);

	    // Derived Query - for checking if User exist by id
	    public boolean existsUserById(String id);


}
