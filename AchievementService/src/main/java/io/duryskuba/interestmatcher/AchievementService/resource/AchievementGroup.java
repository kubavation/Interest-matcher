package io.duryskuba.interestmatcher.AchievementService.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ACHIEVEMENT_GROUP")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AchievementGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACHIEVEMENT_GROUP_ID")
    private Long achievementGroupId;
}
