package io.duryskuba.interestmatcher.AchievementService.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserAchievementDTO {

    private Long achievementId;
    private Long userId;

    private String achievementName;
    private String achievementDesc;

    private Long goal;
    private Long currentValue;
    private int level;

}
