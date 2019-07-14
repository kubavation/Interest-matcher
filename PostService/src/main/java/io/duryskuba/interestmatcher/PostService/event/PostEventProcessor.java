package io.duryskuba.interestmatcher.PostService.event;

import io.duryskuba.interestmatcher.PostService.resource.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Slf4j
@Component
public class PostEventProcessor {

    @Value("${fanouts.post-deletion}")
    private String postDeletionExchange;
    @Value("${fanouts.post-creation}")
    private String postCreationExchange;


    private RabbitTemplate rabbitTemplate;

    public PostEventProcessor(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void emitPostCreationEvent(final PostDto postDto) {
        log.info("EMITING POST CREATION EVENT");
       // rabbitTemplate.convertAndSend(new PostCreationEvent(UUID.randomUUID(), PostDto.builder().postId(1L).build()));
    }

    public void emitPostDeletionEvent(final Long postId) {
        log.info("EMITING POST DELETION EVENT");
        rabbitTemplate.convertAndSend(postCreationExchange, "",
                        new PostDeletionEvent(UUID.randomUUID(), postId));
    }
}
