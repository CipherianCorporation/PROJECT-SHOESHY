package com.edu.graduationproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.entity.UserRole;
import com.edu.graduationproject.model.UserRoleCount;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Query("SELECT DISTINCT a FROM UserRole a WHERE a.user IN ?1")
    List<UserRole> authoritiesOf(List<User> accounts);

    // Native SQL là:
    // select role_id, count(role_id) as 'count' from users_role group by role_id
    @Query("SELECT new com.edu.graduationproject.model.UserRoleCount(u.role.id, COUNT(u.role.id)) FROM UserRole u GROUP BY u.role.id")
    List<UserRoleCount> getUserRoleCount();
}
