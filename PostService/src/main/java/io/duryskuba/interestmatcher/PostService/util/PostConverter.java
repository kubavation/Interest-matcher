package io.duryskuba.interestmatcher.PostService.util;

import io.duryskuba.interestmatcher.PostService.resource.Post;
import io.duryskuba.interestmatcher.PostService.resource.PostDto;

import java.util.Collection;
import java.util.stream.Collectors;

import static io.duryskuba.interestmatcher.PostService.enums.PostStatus.ACTIVE;
import static io.duryskuba.interestmatcher.PostService.enums.PostType.GLOBAL;
import static io.duryskuba.interestmatcher.PostService.enums.PostType.GROUP;

public class PostConverter {

    public static Post toEntity(PostDto dto) {
        return initializePost(dto)
                .authorId(dto.getAuthorId())
                .content(dto.getContent())
                .postStatus(ACTIVE)
                .build();
    }

    public static PostDto toDTO(Post post) {
        return PostDto.builder()
                    .postId(post.getPostId())
                    .authorId(post.getAuthorId())
                    .content(post.getContent())
                    .createdAt(post.getCreatedAt())
                    .groupId(post.getGroupId())
                    .postType(post.getPostType())
                    .build();

    }

    public static Collection<PostDto> toDTOCollection(Collection<Post> entities) {
        return entities
                    .stream()
                    .map(PostConverter::toDTO)
                    .collect(Collectors.toList());
    }

    public static Collection<Post> toEntityCollection(Collection<PostDto> dtos) {
        return dtos
                .stream()
                .map(PostConverter::toEntity)
                .collect(Collectors.toList());
    }


    private static Post.PostBuilder initializePost(PostDto dto) {
        Post.PostBuilder builder = Post.builder();
        if( dto.getGroupId() != null)
            return builder
                    .groupId(dto.getGroupId())
                    .postType(GROUP);
        else
            return builder
                    .postType(GLOBAL);
    }
}
