package io.duryskuba.interestmatcher.GroupService.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class GroupMemberId implements Serializable {

    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "GROUP_ID")
    private Long groupId;

}
