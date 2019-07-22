package io.duryskuba.interestmatcher.AchievementService.util;

import io.duryskuba.interestmatcher.AchievementService.resource.Achievement;
import io.duryskuba.interestmatcher.AchievementService.resource.UserAchievement;
import io.duryskuba.interestmatcher.AchievementService.resource.UserAchievementDTO;

public class UserAchievementConverter {

    public static UserAchievementDTO toDTO(UserAchievement userAchievement,
                                           Achievement achievement) {
        return UserAchievementDTO.builder()
                .achievementDesc(achievement.getDescription())
                .achievementId(achievement.getAchievementId())
                .achievementName(achievement.getName())
                .goal(achievement.getGoal())
                .userId(userAchievement.getUserAchievementId().getUserId())
                .currentValue(userAchievement.getValue())
                .level(achievement.getLevel())
                .build();
    }
}
