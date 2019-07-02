package io.duryskuba.interestmatcher.TagService.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private PostDTO post;
    private Long subscriber;
    private Collection<Tag> tags;

    public static NotificationDTO of(PostDTO postDTO, Long subscriber, Collection<Tag> tags) {
        return new NotificationDTO(postDTO, subscriber, tags);
    }

}
