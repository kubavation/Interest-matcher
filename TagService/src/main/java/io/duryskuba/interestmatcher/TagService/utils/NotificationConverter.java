package io.duryskuba.interestmatcher.TagService.utils;

import io.duryskuba.interestmatcher.TagService.resource.NotificationDTO;
import io.duryskuba.interestmatcher.TagService.resource.PostDTO;
import io.duryskuba.interestmatcher.TagService.resource.Tag;

import java.util.Collection;

import static io.duryskuba.interestmatcher.TagService.resource.NotificationType.POST;

public class NotificationConverter {

    public static NotificationDTO toTagNotification(Long subscriberId, Collection<Tag> tags, PostDTO post) {

        return
                NotificationDTO.builder()
                        .subscriber(subscriberId)
                        .tags(tags)
                        .content(post.getContent())
                        .author(post.getAuthor())
                        .type(POST) //POST_TAG? //todo other: POST, GROUP_POST ...
                        .objectId(post.getPostId())
                        .build();
    }




}
