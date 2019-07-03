package io.duryskuba.interestmatcher.TagService;

import io.duryskuba.interestmatcher.TagService.repository.TagSubscriberRepository;
import io.duryskuba.interestmatcher.TagService.resource.TagSubscriber;
import io.duryskuba.interestmatcher.TagService.resource.TagSubscriberId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TagServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TagServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner loadData(TagSubscriberRepository tagSubscriberRepository) {

		return (args) ->
			tagSubscriberRepository.save(new TagSubscriber(new TagSubscriberId("ala",1L)));
	}

}
