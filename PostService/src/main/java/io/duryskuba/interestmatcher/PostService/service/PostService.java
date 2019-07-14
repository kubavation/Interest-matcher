package io.duryskuba.interestmatcher.PostService.service;

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

import static io.duryskuba.interestmatcher.PostService.util.PostConverter.*;

@Service
public class PostService {

    private PostRepository postRepository;
    private WebClient webClient;

    public PostService(PostRepository postRepository,
                       WebClient webClient) {
        this.postRepository = postRepository;
        this.webClient = webClient;
    }


    public Collection<PostDto> findAll() {
      return toDTOCollection( postRepository.findAll() );
    }

    public PostDto findById(Long id) {
        return toDTO( postRepository.findById(id)
                        .orElseThrow(RuntimeException::new) );
    }

    @Transactional
    public PostDto create(PostDto dto) {

        PostDto result = webClient
            .post()
            .uri("http://tag-service/tags/create-content")
            .body(BodyInserters.fromObject(dto))
            .exchange()
                .block()
                .toEntity(PostDto.class)
                .block()
                    .getBody();

        return toDTO( postRepository.save(toEntity(result)) );
    }
}
