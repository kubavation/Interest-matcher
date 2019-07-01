package io.duryskuba.interestmatcher.TagService.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "POST_TAG")
@Data
@NoArgsConstructor
public class PostTag {

    @EmbeddedId
    private PostTagId id;

    @CreationTimestamp
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    public PostTag(PostTagId id) {
        this.id = id;
    }
}
