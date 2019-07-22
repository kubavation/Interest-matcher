package io.duryskuba.interestmatcher.AchievementService.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ACHIEVEMENT_GROUP")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AchievementGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACHIEVEMENT_GROUP_ID")
    private Long achievementGroupId;

    private String name;

    @OneToMany(mappedBy = "achievementGroup")
    @OrderBy(value = "level ASC")
    private List<Achievement> achievements;

}
