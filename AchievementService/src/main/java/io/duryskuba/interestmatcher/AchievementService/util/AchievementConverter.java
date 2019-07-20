package io.duryskuba.interestmatcher.AchievementService.util;

import io.duryskuba.interestmatcher.AchievementService.resource.Achievement;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementDTO;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementGroup;

public class AchievementConverter {

    public static AchievementDTO toDto(Achievement achievement, Long currentValue) {
        return AchievementDTO.builder()
                    .achievementGroupId(achievement.getAchievementGroup().getAchievementGroupId())
                    .achievementId(achievement.getAchievementId())
                    .description(achievement.getDescription())
                    .goal(achievement.getGoal())
                    .actual(currentValue)
                    .groupName(achievement.getAchievementGroup().getName())
                    .name(achievement.getName())
                    .build();
    }

    public Achievement toEntity(AchievementDTO dto, AchievementGroup group) {
        return Achievement.builder()
                    .achievementGroup(group)
                    .goal(dto.getGoal())
                    .level(dto.getLevel())
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .build();
    }
}
