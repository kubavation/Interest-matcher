package io.duryskuba.interestmatcher.PostService.event;

import io.duryskuba.interestmatcher.PostService.resource.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Slf4j
@Component
public class PostEventProcessor {

    private RabbitTemplate rabbitTemplate;

    public PostEventProcessor(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void emitPostCreation(@NotNull PostDto postDto) {
        log.debug("EMITINT POST CREATION EVENT");
       // rabbitTemplate.convertAndSend(new PostCreationEvent(UUID.randomUUID(), PostDto.builder().postId(1L).build()));
    }
}
