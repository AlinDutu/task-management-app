package com.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class TaskManagementApplication {

	public static void main(String[] args) {

		// Intentionally don't pass args because they are a security hotspot (as identified by Sonar) and not required here
		SpringApplication.run(TaskManagementApplication.class);
	}
}
