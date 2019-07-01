package io.duryskuba.interestmatcher.PostService.resource;

import io.duryskuba.interestmatcher.PostService.enums.PostStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "POST")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long postId;

    private String content;
    @Column(name = "AUTHOR_ID")
    private Long authorId;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private PostStatus postStatus;

}
