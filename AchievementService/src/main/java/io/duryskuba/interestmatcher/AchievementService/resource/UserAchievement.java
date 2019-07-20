package io.duryskuba.interestmatcher.AchievementService.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER_ACHIEVEMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAchievement {

    @EmbeddedId
    private UserAchievementId userAchievementId;

    @Enumerated(EnumType.STRING)
    private AchievementGoalStatus status;

    private Long value;

}
