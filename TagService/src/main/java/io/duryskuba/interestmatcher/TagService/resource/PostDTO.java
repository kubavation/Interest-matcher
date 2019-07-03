package io.duryskuba.interestmatcher.TagService.resource;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long postId;
    private String content;
    private String author;

    public PostDTO(Long postId) {
        this.postId = postId;
    }
}
