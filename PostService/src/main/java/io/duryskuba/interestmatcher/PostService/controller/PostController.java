package io.duryskuba.interestmatcher.PostService.controller;

import io.duryskuba.interestmatcher.PostService.resource.PostDto;
import io.duryskuba.interestmatcher.PostService.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RestController
public class PostController {

    private PostService postService;

    public PostController(PostService postService, WebClient webClient) {
        this.postService = postService;
    }



    @GetMapping("/posts")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/posts/users/{id}")
    public ResponseEntity<?> findAllByUser(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<PostDto> create(@RequestBody PostDto postDto) {

        return new ResponseEntity<>(HttpStatus.OK);
    }





    //        PostDto test = new PostDto();
//        test.setPostId(1L);
//        test.setAuthor("author");
//        test.setContent("siema to #ala co #dsa da#a");
//
//        log.error("before call");
//        //todo do serwisu
//        PostDto result = webClient
//                .post()
//                //.uri("http://localhost:8085/tags/create-content")
//                .uri("http://tag-service/tags/create-content")
//                .body(BodyInserters.fromObject(test))
//                .exchange()
//                    .block()
//                    .toEntity(PostDto.class)
//                    .block()
//                        .getBody();
//
//        log.error("after call");
//        log.error(result.toString());


}
