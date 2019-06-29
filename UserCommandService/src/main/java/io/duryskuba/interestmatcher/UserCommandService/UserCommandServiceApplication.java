package io.duryskuba.interestmatcher.UserCommandService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UserCommandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCommandServiceApplication.class, args);
	}

}
