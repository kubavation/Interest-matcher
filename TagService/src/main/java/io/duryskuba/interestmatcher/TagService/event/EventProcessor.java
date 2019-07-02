package io.duryskuba.interestmatcher.TagService.event;

import io.duryskuba.interestmatcher.TagService.repository.TagSubscriberRepository;
import io.duryskuba.interestmatcher.TagService.resource.NotificationDTO;
import io.duryskuba.interestmatcher.TagService.resource.PostDTO;
import io.duryskuba.interestmatcher.TagService.resource.Tag;
import io.duryskuba.interestmatcher.TagService.resource.TagSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.*;

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
                    final NotificationDTO notification
                          = NotificationDTO.builder()
                            .post(post)
                            .tags(e.getValue())
                            .subscriber(e.getKey())
                            .build();

                    log.error("SENDING " + notification.toString());
                    rabbitTemplate
                            .convertAndSend(MessageBuilder
                            .withBody(notification.toString().getBytes()));
                });

    }




}
