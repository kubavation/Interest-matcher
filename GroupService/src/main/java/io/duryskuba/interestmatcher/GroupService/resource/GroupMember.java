package io.duryskuba.interestmatcher.GroupService.resource;

import io.duryskuba.interestmatcher.GroupService.enums.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "GROUP_MEMBER")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupMember {

    @EmbeddedId
    private GroupMemberId groupMemberId;

    @MapsId("USER_ID")
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Member member;

    @MapsId("GROUP_ID")
    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @Enumerated(EnumType.STRING)
    private MemberRole role;
}
