package io.duryskuba.interestmatcher.AchievementService.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserAchievementId implements Serializable {

    @Column(name = "ACHIEVEMENT_GROUP_ID")
    private Long achievementGroupId;
    @Column(name = "USER_ID")
    private Long userId;
}
