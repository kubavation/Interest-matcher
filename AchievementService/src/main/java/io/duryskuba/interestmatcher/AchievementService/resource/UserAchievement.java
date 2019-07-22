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



    public static UserAchievement initialInstance(UserAchievementId id) {
        return new UserAchievement(id, AchievementGoalStatus.IN_PROGRESS, 1L);
    }

    public static UserAchievement initialInstance(UserAchievementId id, Long value) {
        return new UserAchievement(id, AchievementGoalStatus.IN_PROGRESS, value);
    }
}
