package io.duryskuba.interestmatcher.AchievementService;

import io.duryskuba.interestmatcher.AchievementService.resource.Achievement;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementDTO;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementGroup;
import io.duryskuba.interestmatcher.AchievementService.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashSet;

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
			group.setAchievements(new ArrayList<>());
			achievementService.createAchievementGroup(group);

			AchievementDTO achievementDTO1
					= AchievementDTO.builder()
						.achievementGroupId(1L)
						.name("posty 1")
						.description("Dodawanie postow - nowicjusz")
						.goal(50L)
						.level(1).build();
			achievementService.createAchievement(achievementDTO1);

			AchievementDTO achievementDTO2
					= AchievementDTO.builder()
					.achievementGroupId(1L)
					.name("posty 2")
					.description("Dodawanie postow - ekspert")
					.goal(100L)
					.level(2).build();
			achievementService.createAchievement(achievementDTO2);


		};
	}
}
