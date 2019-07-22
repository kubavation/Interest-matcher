package io.duryskuba.interestmatcher.AchievementService.resource;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@NoArgsConstructor
@Data
@Builder
public class AchievementGroupDTO {

    private Long achievementGroupId;
    private String name;
    private Collection<AchievementDTO> achievements;
}