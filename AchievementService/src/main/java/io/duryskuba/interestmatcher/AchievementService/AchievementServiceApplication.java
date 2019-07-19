package io.duryskuba.interestmatcher.AchievementService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class AchievementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AchievementServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return (args) -> System.out.println("init");
	}
}
