package io.duryskuba.interestmatcher.PostService.resource;

import io.duryskuba.interestmatcher.PostService.enums.PostStatus;
import io.duryskuba.interestmatcher.PostService.enums.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "POST")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long postId;

    private String content;

    @Column(name = "AUTHOR_ID")
    private Long authorId;

    private String author;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private PostStatus postStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private PostType postType;

    @Column(name = "GROUP_ID")
    private Long groupId; //w dto groupy beda pobierane jednym zapytaniem

}
