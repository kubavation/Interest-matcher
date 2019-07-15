package io.duryskuba.interestmatcher.TagService.repository;

import io.duryskuba.interestmatcher.TagService.resource.PostTag;
import io.duryskuba.interestmatcher.TagService.resource.PostTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, PostTagId> {

    @Query("from PostTag t where t.id.tagName = :tagName")
    List<PostTag> findAllByTagName(String tagName);

    Collection<PostTag> findAllById_PostId(Long id);
}
