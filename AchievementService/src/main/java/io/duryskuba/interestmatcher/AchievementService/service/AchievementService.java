package io.duryskuba.interestmatcher.AchievementService.service;

import io.duryskuba.interestmatcher.AchievementService.repository.AchievementGroupRepository;
import io.duryskuba.interestmatcher.AchievementService.repository.AchievementRepository;
import io.duryskuba.interestmatcher.AchievementService.repository.UserAchievementRepository;
import io.duryskuba.interestmatcher.AchievementService.resource.*;
import io.duryskuba.interestmatcher.AchievementService.util.AchievementConverter;
import io.duryskuba.interestmatcher.AchievementService.util.AchievementGroupConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import static io.duryskuba.interestmatcher.AchievementService.resource.UserAchievement.initialInstance;

@Service
public class AchievementService {

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

    public AchievementGroupDTO createAchievementGroup(AchievementGroup achievementGroup) {
        return AchievementGroupConverter.toDto(achievementGroupRepository.save(achievementGroup));
    }

    public void deleteAchievementGroup(Long achievementGroupId) {
        achievementGroupRepository.findById(achievementGroupId)
                .ifPresent(achievementGroupRepository::delete);
    }

    public Collection<AchievementGroupDTO> findAllAchievementGroups() {
        return AchievementGroupConverter.toDtoCollection(achievementGroupRepository.findAll());
    }

    public AchievementGroup findAchievementGroupByIdOrThrow(Long id) {
        return achievementGroupRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public AchievementGroupDTO findAchievementGroupDtoById(Long id) {
        return AchievementGroupConverter.toDto(
                achievementGroupRepository.findById(id)
                        .orElseThrow(RuntimeException::new));
    }

    public Optional<AchievementGroup> findAchievementGroupById(Long id) {
        return achievementGroupRepository.findById(id);
    }

    public Collection<AchievementDTO> findAllAchievements() {
        return AchievementConverter.toDtoList(achievementRepository.findAll());
    }

    public AchievementDTO findAchievementByIdOrThrow(Long id) {
        return AchievementConverter.toDto(
                achievementRepository.findById(id)
                        .orElseThrow(RuntimeException::new));
    }

    public Optional<Achievement> findAchievementById(Long id) {
        return achievementRepository.findById(id);
    }

    public AchievementDTO createAchievement(AchievementDTO achievementDTO) {
        return
                AchievementConverter.toDto(
                        findAchievementGroupById(achievementDTO.getAchievementGroupId())
                                .map(g -> AchievementConverter.toEntity(achievementDTO, g))
                                //.map(this::setNextAvailableLevel)
                                .map(achievementRepository::save)
                                .orElseThrow(RuntimeException::new)
                );
    }

    public void deleteAchievement(Long id) {
        achievementRepository.findById(id)
                .ifPresent(achievementRepository::delete);
    }


    public boolean isLevelAvailable(AchievementGroup group, int level) {
        return
                achievementRepository
                        .findByAchievementGroup_AchievementGroupIdAndLevel(group.getAchievementGroupId(), level)
                        .isPresent();
    }

    private boolean isLevelFirst(Achievement achievement) {
        return achievement.getLevel() == 1;
    }

    private Long findAchievementGoalByGroupByIdAndLevel(Long groupId, int level) {
        return
                achievementRepository.findByAchievementGroup_AchievementGroupIdAndLevel(groupId, level)
                        .map(Achievement::getGoal)
                        .orElseThrow(RuntimeException::new); //todo OrElseGet 1

    }

    public boolean initializeAchievementBasedOnLevel(Achievement achievement, Long userId, Long groupId) {

        if (isLevelFirst(achievement)) {
            initStateOfAchievement(achievement, userId);
        } else {
            //maybe orelseGet
            initStateOfAchievement(achievement, userId,
                    findAchievementGoalByGroupByIdAndLevel(groupId, achievement.getLevel() - 1) + 1);
        }

        return true;
    }

    private boolean isAchievementStatusDone(UserAchievement userAchievement) {
        return AchievementGoalStatus.DONE.equals(userAchievement.getStatus());
    }

    private void changeValueAndStatusOfAchievement(UserAchievement userAchievement,
                                                   Achievement achievement) {

        userAchievement.setValue(userAchievement.getValue() + 1);

        if (userAchievement.getValue().equals(achievement.getGoal())) {
            userAchievement.setStatus(AchievementGoalStatus.DONE);
        }

        userAchievementRepository.save(userAchievement);
    }

    public boolean incrementAchievementValueBasedOnStatus(UserAchievement userAchievement,
                                                          Achievement achievement, AchievementGroup group) {

        if ( isAchievementStatusDone(userAchievement) ) {

            if (!isLevelAvailable(group, achievement.getLevel() + 1)) {

                userAchievement.setValue(userAchievement.getValue() + 1);
                userAchievementRepository.save(userAchievement);
            }

            return false;

        } else {
            changeValueAndStatusOfAchievement(userAchievement, achievement);
        }

        return true;
    }


    public void onAchievementAction(AchievementActionDTO action) {

        final AchievementGroup group = findAchievementGroupByIdOrThrow(action.getAchievementGroupId());

        for (Achievement a : group.getAchievements()) {

            if (userAchievementRepository.findById(new UserAchievementId(a.getAchievementId(), action.getUserId()))
                    .map(ua -> incrementAchievementValueBasedOnStatus(ua, a, group))
                    .orElseGet(() ->
                            initializeAchievementBasedOnLevel(a, action.getUserId(), group.getAchievementGroupId()))) {
                return;
            }
        }

    }


    private boolean initStateOfAchievement(Achievement achievement, Long userId) {
        userAchievementRepository.save(
                initialInstance(new UserAchievementId(achievement.getAchievementId(), userId)));
        return false;
    }

    private boolean initStateOfAchievement(Achievement achievement, Long userId, Long value) {
        userAchievementRepository.save(
                initialInstance(new UserAchievementId(achievement.getAchievementId(), userId), value));
        return false;
    }


    private int getNextAchievementLevel(Achievement achievement) {
        return achievement
                .getAchievementGroup()
                .getAchievements().size() + 1;
    }

    public Achievement setNextAvailableLevel(Achievement achievement) {
        achievement.setLevel(getNextAchievementLevel(achievement));
        return achievement;
    }

}