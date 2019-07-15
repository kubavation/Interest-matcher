package io.duryskuba.interestmatcher.TagService.service;

import io.duryskuba.interestmatcher.TagService.event.EventProcessor;
import io.duryskuba.interestmatcher.TagService.event.PostDeletionEvent;
import io.duryskuba.interestmatcher.TagService.repository.PostTagRepository;
import io.duryskuba.interestmatcher.TagService.repository.TagRepository;
import io.duryskuba.interestmatcher.TagService.resource.PostDTO;
import io.duryskuba.interestmatcher.TagService.resource.PostTag;
import io.duryskuba.interestmatcher.TagService.resource.PostTagId;
import io.duryskuba.interestmatcher.TagService.resource.Tag;
import io.duryskuba.interestmatcher.TagService.utils.TagContentBuilder;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TagService {

    private TagRepository tagRepository;
    private PostTagRepository postTagRepository;
    private TagContentBuilder tagContentBuilder;
    private EventProcessor eventProcessor;


    public TagService(TagRepository tagRepository, PostTagRepository postTagRepository,
                      TagContentBuilder tagContentBuilder, EventProcessor eventProcessor) {
        this.tagRepository = tagRepository;
        this.postTagRepository = postTagRepository;
        this.tagContentBuilder = tagContentBuilder;
        this.eventProcessor = eventProcessor;
    }


    public Collection<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Tag create(Tag tag) {

        tagRepository.findById(tag.getName())
            .ifPresent(t -> {
                throw new RuntimeException("This tag already exists");
            });

        return tagRepository.save(tag);
    }

    public Collection<PostDTO> findAllPostsByTag(String tagName) {
        return postTagRepository
                .findAllByTagName(tagName)
                .stream()
                    .map(t -> new PostDTO(t.getId().getPostId()))
                    .collect(Collectors.toList());
    }

    public PostDTO createTagContentFromPost(PostDTO postDTO) {

        Pair<String, List<Tag>> result = tagContentBuilder
                .pullOutTags(postDTO.getContent());

        prepareTagsAndNotify(postDTO, result.getValue1());
        return new PostDTO(postDTO.getPostId(), result.getValue0(), postDTO.getAuthor());
    }

    @Async
    public void prepareTagsAndNotify(PostDTO postDTO, List<Tag> tags) {
            tags
                .stream()
                .map(this::createTagIfNotExists)
                .map(t -> createPostTagEntry(postDTO, t))
                .forEach(t -> log.error("creating " + t)); //notify subscribers

            log.info("calling emitNotificationEvent");
            eventProcessor.emitNotificationEvent(tags, postDTO);
    }


    public Collection<Tag> createTags(Collection<Tag> tags) {
        log.info("creating tags if not exists already");
        return
                tags.stream()
                    .map(this::createTagIfNotExists)
                    .collect(Collectors.toList());
    }


    public Collection<PostTag> createPostTagEntries(PostDTO postDTO, Collection<Tag> tags) {
        log.debug("creating postTag entries");
        return
                tags.stream()
                    .map(t -> new PostTag(new PostTagId(t.getName(), postDTO.getPostId())))
                    .map(this::createPostTagIfNotExists)
                    .collect(Collectors.toList());
    }

    public PostTag createPostTagEntry(PostDTO postDTO, Tag tag) {
        log.debug("creating postTag entry");
        return createPostTagIfNotExists(new PostTag(new PostTagId(tag.getName(), postDTO.getPostId())));
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


    @RabbitListener(queues = "postDeletionQueue")
    public void onPostDeletion(PostDeletionEvent event) {
        postTagRepository
            .findAllById_PostId(event.getPostId())
            .stream()
                .forEach(postTagRepository::delete);
    }



}
