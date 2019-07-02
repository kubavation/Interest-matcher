package io.duryskuba.interestmatcher.TagService.resource;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public class NotificationDTO {

    private PostDTO post;
    private String tagName;
    private Long userId;

}
