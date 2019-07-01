package io.duryskuba.interestmatcher.TagService.resource;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@Data
public class PostTagId implements Serializable {

    @Column(name = "TAG_NAME")
    private String tagName;
    @Column(name = "POST_ID")
    private Long postId;
}
