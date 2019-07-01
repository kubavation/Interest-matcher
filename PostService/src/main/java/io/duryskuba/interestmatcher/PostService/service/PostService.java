package io.duryskuba.interestmatcher.PostService.service;

import io.duryskuba.interestmatcher.PostService.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public 
}
