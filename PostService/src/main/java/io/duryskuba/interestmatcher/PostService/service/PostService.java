package io.duryskuba.interestmatcher.PostService.service;

import io.duryskuba.interestmatcher.PostService.enums.PostStatus;
import io.duryskuba.interestmatcher.PostService.event.PostEventProcessor;
import io.duryskuba.interestmatcher.PostService.exception.ResourceNotFoundException;
import io.duryskuba.interestmatcher.PostService.repository.PostRepository;
import io.duryskuba.interestmatcher.PostService.resource.Post;
import io.duryskuba.interestmatcher.PostService.resource.PostDto;
import io.duryskuba.interestmatcher.PostService.util.PostConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.List;

import static io.duryskuba.interestmatcher.PostService.enums.PostStatus.DELETED;
import static io.duryskuba.interestmatcher.PostService.util.PostConverter.*;

@Service
public class PostService {

    private PostRepository postRepository;
    private WebClient webClient;
    private PostEventProcessor postEventProcessor;

    public PostService(PostRepository postRepository,
                       PostEventProcessor postEventProcessor,
                       WebClient webClient) {
        this.postRepository = postRepository;
        this.postEventProcessor = postEventProcessor;
        this.webClient = webClient;
    }


    public Collection<PostDto> findAll() {
      return toDTOCollection( postRepository.findAll() );
    }

    public PostDto findById(Long id) {
        return toDTO( postRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(id)) );
    }

    public Collection<PostDto> findAllByUser(Long id) {
        return toDTOCollection( postRepository.findAllByAuthorId(id) );
    }

    @Transactional
    public PostDto create(PostDto dto) {

        Post post = postRepository.save(toEntity(dto));
        post.setContent( createContent(toDTO(post)).getContent() );

        return toDTO(post);
        //return toDTO( postRepository.save(toEntity(result)) );
    }


    public void delete(Long postId) {

        //check if author == user

        postRepository.findById(postId)
            .ifPresent(p -> {
                p.setPostStatus(DELETED);
                postEventProcessor.emitPostDeletionEvent(postId);
            });
    }


    public PostDto createContent(PostDto dto) {

        return webClient
                .post()
                .uri("http://tag-service/tags/create-content")
                .body(BodyInserters.fromObject(dto))
                .exchange()
                .block()
                .toEntity(PostDto.class)
                .block()
                .getBody();
    }


}
