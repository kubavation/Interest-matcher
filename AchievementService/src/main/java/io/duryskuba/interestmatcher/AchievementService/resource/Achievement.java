package io.duryskuba.interestmatcher.AchievementService.resource;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
   // @JsonIgnore
    private AchievementGroup achievementGroup;

    private int level;

    private Long goal;


    //stream listener dla grupy -> i update na kazdym achievemencie z tej grupy
}
