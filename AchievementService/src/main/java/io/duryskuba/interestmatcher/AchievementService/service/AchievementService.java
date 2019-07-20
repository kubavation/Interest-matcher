package io.duryskuba.interestmatcher.AchievementService.service;

import io.duryskuba.interestmatcher.AchievementService.repository.AchievementGroupRepository;
import io.duryskuba.interestmatcher.AchievementService.repository.AchievementRepository;
import io.duryskuba.interestmatcher.AchievementService.repository.UserAchievementRepository;
import io.duryskuba.interestmatcher.AchievementService.resource.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

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
        achievement.setLevel( getNextAchievementLevel(achievement) );
        return achievementRepository.save(achievement);
    }

    public void deleteAchievement(Long id) {
        achievementRepository.findById(id)
                .ifPresent(achievementRepository::delete);
    }

    public void todonameofmethodforincrementingachievement(AchievementActionDTO action) {
        AchievementGroup group =
                 findAchievementGroupById(action.getAchievementGroupId());

        Set<Achievement> achievements = group.getAchievements();

        final Long userId = action.getUserId();

        for( Achievement a : achievements) {

            Optional<UserAchievement> userAchievementOpt = userAchievementRepository
                    .findById(new UserAchievementId(a.getAchievementId(), action.getUserId()));

            if ( userAchievementOpt.isPresent() ) {

                final UserAchievement userAchievement = userAchievementOpt.get();

                if( AchievementGoalStatus.DONE.equals(userAchievement.getStatus()) )
                    continue;
                else {
                    //increment value of achievement
                    userAchievement.setValue( userAchievement.getValue() + 1);

                    if( userAchievement.getValue().equals(a.getGoal()) ) {
                        userAchievement.setStatus(AchievementGoalStatus.DONE);
                    }

                    break;
                }

            } else {
                //init method for value of achievement
                UserAchievement userAchievement
                        = UserAchievement.builder()
                            .userAchievementId(
                                  new UserAchievementId(a.getAchievementId(), action.getUserId())
                                )
                            .status(AchievementGoalStatus.IN_PROGRESS)
                            .value(1L)
                            .build();

                userAchievementRepository.save(userAchievement);
                break;
            }

        }

    }



    private int getNextAchievementLevel(Achievement achievement) {
        return achievement
                .getAchievementGroup()
                    .getAchievements().size() + 1;
    }



}
