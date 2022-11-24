package com.edu.graduationproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.graduationproject.entity.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
    @Query("SELECT u FROM Visitor u WHERE u.ip = :ip")
    public Optional<List<Visitor>> findAllByIp(String ip);

    @Query("SELECT COUNT(u) FROM Visitor u")
    public Long getCount();
}