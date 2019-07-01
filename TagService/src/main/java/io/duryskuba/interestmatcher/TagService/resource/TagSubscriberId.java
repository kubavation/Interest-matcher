package io.duryskuba.interestmatcher.TagService.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagSubscriberId implements Serializable {

    @Column(name = "TAG_NAME")
    private String tagName;
    @Column(name = "USER_ID")
    private Long userId;
}
