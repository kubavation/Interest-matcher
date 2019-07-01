package io.duryskuba.interestmatcher.TagService.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "POST_TAG")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostTag {

    @EmbeddedId
    private PostTagId id;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
}
