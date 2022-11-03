package com.edu.graduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.graduationproject.entity.Color;

public interface ColorRepository extends JpaRepository<Color, String> {

}
