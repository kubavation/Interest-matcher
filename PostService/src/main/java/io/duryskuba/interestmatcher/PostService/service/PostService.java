package io.duryskuba.interestmatcher.PostService.service;

import io.duryskuba.interestmatcher.PostService.repository.PostRepository;
import io.duryskuba.interestmatcher.PostService.resource.Post;
import io.duryskuba.interestmatcher.PostService.resource.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


//    public List<PostDto> findAll() {
//        postRepository.findAll();
//    }

    public Post create(Post post) {
        return postRepository.save(post);
    }
}
