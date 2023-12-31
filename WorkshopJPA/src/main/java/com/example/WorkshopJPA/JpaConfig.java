package com.example.WorkshopJPA;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.example.WorkshopJPA.model")
@EnableJpaRepositories(basePackages = "com.example.WorkshopJPA.repository")
public class JpaConfig {
}
