package io.duryskuba.interestmatcher.TagService.event;

import io.duryskuba.interestmatcher.TagService.repository.TagSubscriberRepository;
import io.duryskuba.interestmatcher.TagService.resource.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.*;

import static io.duryskuba.interestmatcher.TagService.resource.NotificationType.INVITATION;
import static io.duryskuba.interestmatcher.TagService.resource.NotificationType.POST;

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

                      final NotificationDTO notificationDTO =
                              NotificationDTO.builder()
                                .subscriber(e.getKey())
                                .tags(e.getValue())
                                .content(post.getContent())
                                .author(post.getAuthor())
                                .type(POST)
                                .objectId(post.getPostId())
                                .build();

                      log.error("SENDING ");
                      rabbitTemplate.convertAndSend("notificationExchange","",
                              notificationDTO);

                    //todo remove ( for tests )
                    try {
                        System.out.println("sleeping");
                        Thread.sleep(1000);
                        rabbitTemplate.convertAndSend("notificationExchange", "",
                                NotificationDTO.builder().objectId(2L).author("author2").type(INVITATION).build());

                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });

    }




}
