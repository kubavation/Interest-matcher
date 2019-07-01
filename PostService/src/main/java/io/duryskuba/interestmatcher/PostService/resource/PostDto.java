package io.duryskuba.interestmatcher.PostService.resource;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
//@Builder
public class PostDto {

    private Long postId;
    private Long authorId;
    private String content;

    public PostDto() {}
}
