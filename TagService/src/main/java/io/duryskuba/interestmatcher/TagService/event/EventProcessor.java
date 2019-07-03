package io.duryskuba.interestmatcher.TagService.event;

import io.duryskuba.interestmatcher.TagService.repository.TagSubscriberRepository;
import io.duryskuba.interestmatcher.TagService.resource.NotificationDTO;
import io.duryskuba.interestmatcher.TagService.resource.PostDTO;
import io.duryskuba.interestmatcher.TagService.resource.Tag;
import io.duryskuba.interestmatcher.TagService.resource.TagSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.*;

import static io.duryskuba.interestmatcher.TagService.resource.NotificationDTO.of;

@Slf4j
@Component
public class EventProcessor {

    private TagSubscriberRepository tagSubscriberRepository;
    private RabbitTemplate rabbitTemplate;

    public EventProcessor(TagSubscriberRepository tagSubscriberRepository,
                          RabbitTemplate rabbitTemplate) {
        this.tagSubscriberRepository = tagSubscriberRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Async
    public void emitNotificationEvent(Collection<Tag> toEmit, final PostDTO post) {

        Map<Long, HashSet<Tag>> notified = new HashMap<>();

        toEmit
                .stream()
                .forEach(t -> {
                    System.out.println(t);
                    tagSubscriberRepository
                            .findAllById_TagName(t.getName()).stream()
                            .forEach(u -> {
                                if (!notified.containsKey(u.getId().getUserId()))
                                    notified.put(u.getId().getUserId(), new HashSet<>(Arrays.asList(t)));
                                else
                                    notified.get(u.getId().getUserId()).add(t);
                            });
                });

        notified
                .entrySet()
                .forEach(e -> {
                    log.error("SENDING ");
                    rabbitTemplate
                            .convertAndSend(MessageBuilder
                            .withBody(of(post,e.getKey(),e.getValue()).toString().getBytes())
                            .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                            .build());
                });

    }



    @RabbitListener
    public void test(NotificationDTO notificationDTO) {
        System.out.println("RECEIVED " + notificationDTO.toString());
    }




}
