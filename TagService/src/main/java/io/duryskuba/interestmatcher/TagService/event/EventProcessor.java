package io.duryskuba.interestmatcher.TagService.event;

import io.duryskuba.interestmatcher.TagService.repository.TagSubscriberRepository;
import io.duryskuba.interestmatcher.TagService.resource.*;
import io.duryskuba.interestmatcher.TagService.utils.CollectionUtils;
import io.duryskuba.interestmatcher.TagService.utils.NotificationConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.duryskuba.interestmatcher.TagService.resource.NotificationType.INVITATION;
import static io.duryskuba.interestmatcher.TagService.resource.NotificationType.POST;
import static io.duryskuba.interestmatcher.TagService.utils.NotificationConverter.toTagNotification;

@Slf4j
@Component
public class EventProcessor {

    private TagSubscriberRepository tagSubscriberRepository;
    private RabbitTemplate rabbitTemplate;

    @Value("${fanouts.tag-notification}")
    private String tagNotificationExchange;

    public EventProcessor(TagSubscriberRepository tagSubscriberRepository,
                          RabbitTemplate rabbitTemplate) {
        this.tagSubscriberRepository = tagSubscriberRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Async
    public void emitTagNotificationEvent(List<Tag> toEmit, final PostDTO post) {

        Map<Long, HashSet<Tag>> notified = new HashMap<>();

        toEmit
            .forEach(t -> {
                    log.info(t.toString());
                    tagSubscriberRepository
                            .findAllById_TagName(t.getName())
                            .forEach(u -> {
                                log.info(u.toString());
                                notified.merge(u.getId().getUserId(), CollectionUtils.of(t),
                                        CollectionUtils::merge);
                            }
                    );});

        notified
                .entrySet()
                .forEach(e -> {
                      log.info("SENDING");
                      rabbitTemplate.convertAndSend(tagNotificationExchange,"",
                              toTagNotification(e.getKey(), e.getValue(), post));

                });

    }








    /*

        if (!notified.containsKey(u.getId().getUserId()))
            notified.put(u.getId().getUserId(), new HashSet<>(Arrays.asList(t)));
        else
            notified.get(u.getId().getUserId()).add(t);
     */


}
