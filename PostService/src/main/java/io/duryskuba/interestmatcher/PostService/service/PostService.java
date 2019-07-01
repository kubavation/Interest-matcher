package io.duryskuba.interestmatcher.PostService.service;

import io.duryskuba.interestmatcher.PostService.repository.PostRepository;
import io.duryskuba.interestmatcher.PostService.resource.Post;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public Post create(Post post) {
        return postRepository.save(post);
    }
}
