package io.duryskuba.interestmatcher.AchievementService.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACHIEVEMENT_ID")
    private Long achievementId;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "achievement_group_id")
    private AchievementGroup achievementGroup;

    private int level;

    private Long goal;


    //stream listener dla grupy -> i update na kazdym achievemencie z tej grupy
}
