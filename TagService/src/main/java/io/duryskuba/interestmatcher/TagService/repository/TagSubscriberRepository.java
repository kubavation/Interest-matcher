package io.duryskuba.interestmatcher.TagService.repository;

import io.duryskuba.interestmatcher.TagService.resource.TagSubscriber;
import io.duryskuba.interestmatcher.TagService.resource.TagSubscriberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TagSubscriberRepository extends JpaRepository<TagSubscriber, TagSubscriberId> {

    Collection<TagSubscriber> findAllById_TagName(String tagName);
}
