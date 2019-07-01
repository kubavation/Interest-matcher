package io.duryskuba.interestmatcher.TagService.service;

import io.duryskuba.interestmatcher.TagService.repository.PostTagRepository;
import io.duryskuba.interestmatcher.TagService.repository.TagRepository;
import io.duryskuba.interestmatcher.TagService.resource.PostDTO;
import io.duryskuba.interestmatcher.TagService.resource.PostTag;
import io.duryskuba.interestmatcher.TagService.resource.PostTagId;
import io.duryskuba.interestmatcher.TagService.resource.Tag;
import io.duryskuba.interestmatcher.TagService.utils.TagContentBuilder;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostTagRepository postTagRepository;

    @Autowired
    private TagContentBuilder tagContentBuilder;



    public PostDTO createTagContentFromPost(PostDTO postDTO) {
        return new PostDTO();
    }


    public List<Tag> createTags(List<Tag> tags) {
        log.debug("creating tags if not exists already");

    }



    public List<PostTag> createPostTagEntries(PostDTO postDTO, Collection<Tag> tags) {
        log.debug("creating postTag entries");
        return
                tags.stream()
                    .map(t -> new PostTag(new PostTagId(t.getName(), postDTO.getPostId())))
                    .map(this::createPostTagIfNotExists)
                    .collect(Collectors.toList());
    }



    private PostTag createPostTagIfNotExists(PostTag postTag) {
        return
            postTagRepository
                .findById(postTag.getId())
                .orElse(postTagRepository.save(postTag));
    }

    private Tag createTagIfNotExists(Tag tag) {
        return
            tagRepository
                .findById(tag.getName())
                .orElse(tagRepository.save(tag));
    }

}
