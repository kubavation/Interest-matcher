package io.duryskuba.interestmatcher.PostService.resource;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {

    private Long postId;
    private Long authorId;
}
