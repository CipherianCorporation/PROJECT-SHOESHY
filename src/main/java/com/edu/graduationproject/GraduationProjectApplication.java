package com.edu.graduationproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableScheduling
@EnableEncryptableProperties
public class GraduationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraduationProjectApplication.class, args);
	}

}
