package io.duryskuba.interestmatcher.AchievementService.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;

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
               fetch = FetchType.LAZY)
    @OrderBy(value = "level ASC")
    @JsonIgnore //todo converting child components to DTO
    private Set<Achievement> achievements;

}
