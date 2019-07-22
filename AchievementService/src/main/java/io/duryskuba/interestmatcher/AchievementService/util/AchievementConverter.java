package io.duryskuba.interestmatcher.AchievementService.util;

import io.duryskuba.interestmatcher.AchievementService.resource.Achievement;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementDTO;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementGroup;

import java.util.Collection;
import java.util.stream.Collectors;

public class AchievementConverter {

    //achievement status DTO ??
    public static AchievementDTO toDto(Achievement achievement) {
        return AchievementDTO.builder()
                    .achievementGroupId(achievement.getAchievementGroup().getAchievementGroupId())
                    .achievementId(achievement.getAchievementId())
                    .description(achievement.getDescription())
                    .goal(achievement.getGoal())
                    .groupName(achievement.getAchievementGroup().getName())
                    .name(achievement.getName())
                    .build();
    }

    public static Achievement toEntity(AchievementDTO dto, AchievementGroup group) {
        return Achievement.builder()
                    .achievementGroup(group)
                    .goal(dto.getGoal())
                    .level(dto.getLevel())
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .build();
    }

    public static Collection<AchievementDTO> toDtoList(Collection<Achievement> achievements) {
        return achievements.stream()
                    .map(AchievementConverter::toDto)
                        .collect(Collectors.toList());
    }
    

}
