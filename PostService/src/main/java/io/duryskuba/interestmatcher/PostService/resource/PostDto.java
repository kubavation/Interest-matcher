package io.duryskuba.interestmatcher.PostService.resource;

import io.duryskuba.interestmatcher.PostService.enums.PostType;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostDto {

    private Long postId;
    private Long authorId;
    private String author;
    private String content;

    private Long groupId;
    private LocalDateTime createdAt;
    private PostType postType;

    //todo moze oddzielne dto dla global/group?

}
