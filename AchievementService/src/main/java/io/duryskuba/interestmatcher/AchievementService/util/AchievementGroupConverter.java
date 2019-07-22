package io.duryskuba.interestmatcher.AchievementService.util;

import io.duryskuba.interestmatcher.AchievementService.resource.AchievementGroup;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementGroupDTO;

public class AchievementGroupConverter {

    public static AchievementGroupDTO toDto(AchievementGroup achievementGroup) {
        return AchievementGroupDTO.builder()
                    .name(achievementGroup.getName())
                    .achievementGroupId(achievementGroup.getAchievementGroupId())
                    .achievements(
                        AchievementConverter.toDtoList(achievementGroup.getAchievements()))
                    .build();
    }
}
