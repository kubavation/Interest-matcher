package io.duryskuba.interestmatcher.PostService.service;

import io.duryskuba.interestmatcher.PostService.repository.PostRepository;
import io.duryskuba.interestmatcher.PostService.resource.Post;
import io.duryskuba.interestmatcher.PostService.resource.PostDto;
import io.duryskuba.interestmatcher.PostService.util.PostConverter;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static io.duryskuba.interestmatcher.PostService.util.PostConverter.toDTO;
import static io.duryskuba.interestmatcher.PostService.util.PostConverter.toDTOCollection;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public Collection<PostDto> findAll() {
      return toDTOCollection( postRepository.findAll() );
    }

    public PostDto findById(Long id) {
        return toDTO( postRepository.findById(id)
                        .orElseThrow(RuntimeException::new) );
    }


    public Post create(Post post) {
        return postRepository.save(post);
    }
}
