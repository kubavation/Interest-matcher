package io.duryskuba.interestmatcher.AchievementService.repository;

import io.duryskuba.interestmatcher.AchievementService.resource.UserAchievement;
import io.duryskuba.interestmatcher.AchievementService.resource.UserAchievementId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAchievementRepository extends JpaRepository<UserAchievement, UserAchievementId> {
}
