package io.duryskuba.interestmatcher.AchievementService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AchievementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AchievementServiceApplication.class, args);
	}

}
