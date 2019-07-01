package io.duryskuba.interestmatcher.TagService.repository;

import io.duryskuba.interestmatcher.TagService.resource.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
}
