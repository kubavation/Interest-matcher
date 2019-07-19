package io.duryskuba.interestmatcher.AchievementService.service;

import io.duryskuba.interestmatcher.AchievementService.repository.AchievementGroupRepository;
import io.duryskuba.interestmatcher.AchievementService.repository.AchievementRepository;
import io.duryskuba.interestmatcher.AchievementService.repository.UserAchievementRepository;
import io.duryskuba.interestmatcher.AchievementService.resource.Achievement;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementActionDTO;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementGroup;
import org.springframework.scheduling.annotation.Async;
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

    public Collection<AchievementGroup> findAllAchievementGroups() {
        return achievementGroupRepository.findAll();
    }

    public AchievementGroup findAchievementGroupById(Long id) {
        return achievementGroupRepository.findById(id)
                    .orElseThrow(RuntimeException::new);
    }

    public Collection<Achievement> findAllAchievements() {
        return achievementRepository.findAll();
    }

    public Achievement findAchievementById(Long id) {
        return achievementRepository.findById(id)
                    .orElseThrow(RuntimeException::new);
    }

    //todo refactor
    public Achievement createAchievement(Achievement achievement, Long groupId) {
        AchievementGroup group = findAchievementGroupById(groupId);
        achievement.setAchievementGroup(group);
        return achievementRepository.save(achievement);
    }

    public void deleteAchievement(Long id) {
        achievementRepository.findById(id)
                .ifPresent(achievementRepository::delete);
    }

    //listener for createUserQueue
//    @Async
//    public void initAchievementsOfUser(Long userId) {
//        findAllAchievementGroups()
//                .stream()
//                .map(AchievementGroup::getAchievements)
//                    .map()
//    }
//
//
//    public void todonameofmethodforincrementingachievement(AchievementActionDTO action) {
//        AchievementGroup group =
//                 findAchievementGroupById(action.getAchievementGroupId());
//
//        group.getAchievements()
//                .
//
//    }

}
