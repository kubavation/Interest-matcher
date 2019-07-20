package io.duryskuba.interestmatcher.AchievementService.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AchievementDTO {

    private Long achievementId;
    private Long achievementGroupId;

    private String name;
    private String description;

    private String groupName;

    private int level;
    private Long goal;
    private Long actual;


}
