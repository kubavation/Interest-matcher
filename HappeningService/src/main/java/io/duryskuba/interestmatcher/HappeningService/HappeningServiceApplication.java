package io.duryskuba.interestmatcher.HappeningService;

import io.duryskuba.interestmatcher.HappeningService.repository.HappeningRepository;
import io.duryskuba.interestmatcher.HappeningService.resource.Happening;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

@Slf4j
@EnableEurekaClient
@SpringBootApplication
public class HappeningServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappeningServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(HappeningRepository happeningRepository) {
		return (args) -> {
			//happeningRepository.save(new Happening(UUID.randomUUID().toString(), "testowe"));

			List<Happening> happ = happeningRepository.findAll();
			happ.forEach(System.out::println);
		};
	}
}
