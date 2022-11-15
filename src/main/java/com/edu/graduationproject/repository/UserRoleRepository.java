package com.edu.graduationproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.graduationproject.entity.User;
import com.edu.graduationproject.entity.UserRole;
import com.edu.graduationproject.model.IUserRoleCount;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Query("SELECT DISTINCT a FROM UserRole a WHERE a.user IN ?1")
    List<UserRole> authoritiesOf(List<User> accounts);

    // Native SQL là:
    // select role_id, count(role_id) as 'count' from user_roles group by role_id
    @Query(value = "SELECT u.role_id as roleId, COUNT(u.role_id) as count FROM user_roles u GROUP BY u.role_id", nativeQuery = true)
    List<IUserRoleCount> getUserRoleCount();
}
