package io.duryskuba.interestmatcher.PostService.repository;

import io.duryskuba.interestmatcher.PostService.resource.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Collection<Post> findAllByAuthorId(Long id);
}
