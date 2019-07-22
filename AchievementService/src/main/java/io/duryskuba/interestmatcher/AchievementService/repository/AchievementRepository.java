package io.duryskuba.interestmatcher.AchievementService.repository;

import io.duryskuba.interestmatcher.AchievementService.resource.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    Optional<Achievement> findByAchievementGroup_AchievementGroupIdAndLevel(Long id, int level);
}
