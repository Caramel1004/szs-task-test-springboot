package com.szs.task;

import com.szs.task.domain.configration.JwtConfigProps;
import com.szs.task.domain.configration.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;


//@EnableConfigurationProperties({JwtConfigProps.class})
@EnableJpaRepositories(basePackages = "com.szs.task.domain.repository")
@SpringBootApplication(scanBasePackages = "com.szs.task.*")
public class TaskApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

}
