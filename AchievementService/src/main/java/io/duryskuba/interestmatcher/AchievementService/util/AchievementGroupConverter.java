package io.duryskuba.interestmatcher.AchievementService.util;

import io.duryskuba.interestmatcher.AchievementService.resource.AchievementGroup;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementGroupDTO;

import java.util.Collection;
import java.util.stream.Collectors;

public class AchievementGroupConverter {

    public static AchievementGroupDTO toDto(AchievementGroup achievementGroup) {
        return AchievementGroupDTO.builder()
                    .name(achievementGroup.getName())
                    .achievementGroupId(achievementGroup.getAchievementGroupId())
                    .achievements(
                        AchievementConverter.toDtoList(achievementGroup.getAchievements()))
                    .build();
    }

    public static Collection<AchievementGroupDTO> toDtoCollection(Collection<AchievementGroup> achievementGroups) {
        return achievementGroups.stream()
                    .map(AchievementGroupConverter::toDto)
                    .collect(Collectors.toList());
    }
}
