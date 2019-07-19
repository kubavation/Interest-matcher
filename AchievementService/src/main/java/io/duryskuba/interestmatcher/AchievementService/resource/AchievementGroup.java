package io.duryskuba.interestmatcher.AchievementService.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    private String name;

    @OneToMany(mappedBy = "achievementGroup",
               fetch = FetchType.LAZY,
               orphanRemoval = true)
    private Set<Achievement> achievements;

}
