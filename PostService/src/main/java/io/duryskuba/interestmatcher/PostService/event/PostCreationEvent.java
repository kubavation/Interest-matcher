package io.duryskuba.interestmatcher.PostService.event;

import io.duryskuba.interestmatcher.PostService.resource.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PostCreationEvent {

    private UUID id;
    private PostDto postDto;
}
