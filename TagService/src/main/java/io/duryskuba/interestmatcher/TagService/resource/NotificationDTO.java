package io.duryskuba.interestmatcher.TagService.resource;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Set;

@Builder
@Data
public class NotificationDTO {

    private PostDTO post;
    private Long subscriber;
    private Collection<Tag> tags;

}
