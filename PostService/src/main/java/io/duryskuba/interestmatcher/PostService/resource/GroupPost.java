package io.duryskuba.interestmatcher.PostService.resource;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@RequiredArgsConstructor
@Data
public class GroupPost extends Post {

    @Column(name = "GROUP_ID")
    private Long groupId;
}
