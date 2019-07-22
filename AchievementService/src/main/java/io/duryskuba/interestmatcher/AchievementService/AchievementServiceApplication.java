package io.duryskuba.interestmatcher.AchievementService;

import io.duryskuba.interestmatcher.AchievementService.resource.Achievement;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementGroup;
import io.duryskuba.interestmatcher.AchievementService.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
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


	@Autowired
	private AchievementService achievementService;

	@Bean
	CommandLineRunner init() {
		return (args) -> {
			System.out.println("init");
			AchievementGroup group = new AchievementGroup();
			group.setName("Tworzenie postów");
			achievementService.createAchivementGroup(group);



		};
	}
}
