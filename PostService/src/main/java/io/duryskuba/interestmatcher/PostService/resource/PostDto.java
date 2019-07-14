package io.duryskuba.interestmatcher.PostService.resource;

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

}
