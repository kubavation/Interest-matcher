package io.duryskuba.interestmatcher.AchievementService.service;

import io.duryskuba.interestmatcher.AchievementService.repository.AchievementGroupRepository;
import io.duryskuba.interestmatcher.AchievementService.repository.AchievementRepository;
import io.duryskuba.interestmatcher.AchievementService.repository.UserAchievementRepository;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementGroup;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AchievementService {

    //todo refactor to other services
    private AchievementGroupRepository achievementGroupRepository;
    private AchievementRepository achievementRepository;
    private UserAchievementRepository userAchievementRepository;

    public AchievementService(AchievementGroupRepository achievementGroupRepository,
                              AchievementRepository achievementRepository,
                              UserAchievementRepository userAchievementRepository) {
        this.achievementGroupRepository = achievementGroupRepository;
        this.achievementRepository = achievementRepository;
        this.userAchievementRepository = userAchievementRepository;
    }


    //todo change to dto
    public AchievementGroup createAchivementGroup(AchievementGroup achievementGroup) {
        return achievementGroupRepository.save(achievementGroup);
    }

    public void deleteAchievementGroup(Long achievementGroupId) {
        achievementGroupRepository.findById(achievementGroupId)
                    .ifPresent(achievementGroupRepository::delete);
    }

    public Collection<AchievementGroup> findAll() {
        return achievementGroupRepository.findAll();
    }

    public AchievementGroup findById(Long id) {
        return achievementGroupRepository.findById(id)
                    .orElseThrow(RuntimeException::new);
    }

}
