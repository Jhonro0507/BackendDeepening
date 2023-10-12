package com.OpenFeign.WorkshopSpringValidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WorkshopSpringValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkshopSpringValidatorApplication.class, args);
	}

}