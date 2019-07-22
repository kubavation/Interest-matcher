package io.duryskuba.interestmatcher.AchievementService.repository;

import io.duryskuba.interestmatcher.AchievementService.resource.Achievement;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AchievementGroupRepository extends JpaRepository<AchievementGroup, Long> {
}
