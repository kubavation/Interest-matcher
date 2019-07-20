package io.duryskuba.interestmatcher.AchievementService.util;

import io.duryskuba.interestmatcher.AchievementService.resource.Achievement;
import io.duryskuba.interestmatcher.AchievementService.resource.UserAchievement;
import io.duryskuba.interestmatcher.AchievementService.resource.UserAchievementDTO;

public class UserAchievementConverter {

    public static UserAchievementDTO toDTO(UserAchievement userAchievement,
                                           Achievement achievement) {
        return UserAchievementDTO.builder().build();
    }
}
