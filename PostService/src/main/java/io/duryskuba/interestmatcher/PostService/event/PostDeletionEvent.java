package io.duryskuba.interestmatcher.PostService.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDeletionEvent {

    private UUID id;
    private Long postId;
}
