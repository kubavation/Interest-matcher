package io.duryskuba.interestmatcher.TagService.controller;

import io.duryskuba.interestmatcher.TagService.resource.PostDTO;
import io.duryskuba.interestmatcher.TagService.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TagController {

    private TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/tags/create-content")
    public ResponseEntity<PostDTO> createTagContent(@RequestBody PostDTO postDTO) {
        log.error("???");
        return
            new ResponseEntity<>(tagService.createTagContentFromPost(postDTO), HttpStatus.CREATED);
    }

}
