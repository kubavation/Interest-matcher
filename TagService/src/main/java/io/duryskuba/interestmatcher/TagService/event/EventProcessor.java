package io.duryskuba.interestmatcher.TagService.event;

import io.duryskuba.interestmatcher.TagService.repository.TagSubscriberRepository;
import io.duryskuba.interestmatcher.TagService.resource.PostDTO;
import io.duryskuba.interestmatcher.TagService.resource.Tag;
import io.duryskuba.interestmatcher.TagService.resource.TagSubscriber;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EventProcessor {

    private TagSubscriberRepository tagSubscriberRepository;
    private RabbitTemplate rabbitTemplate

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
                    tagSubscriberRepository
                            .findAllById_TagName(t.getName()).stream()
                            .forEach(u -> {
                                if (!notified.containsKey(u.getId().getUserId()))
                                    notified.put(u.getId().getUserId(), new HashSet<>(Arrays.asList(t)));
                                else
                                    notified.get(u.getId().getUserId()).add(t);
                            });
                });

        /**
         * jakbym chcial przesylac jakie tagi zostaly przeslane to musze uzyc mapy
         */
        notified.entrySet()
                .forEach(e -> {
                    rabbitTemplate.convertAndSend(messagingUtils
                            .createMessage(prepareNotification(post, e.getKey(), e.getValue())));

                });
    }


}
