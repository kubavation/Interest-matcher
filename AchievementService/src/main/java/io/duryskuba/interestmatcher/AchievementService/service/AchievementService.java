package io.duryskuba.interestmatcher.AchievementService.service;

import io.duryskuba.interestmatcher.AchievementService.repository.AchievementGroupRepository;
import io.duryskuba.interestmatcher.AchievementService.repository.AchievementRepository;
import io.duryskuba.interestmatcher.AchievementService.repository.UserAchievementRepository;
import io.duryskuba.interestmatcher.AchievementService.resource.*;
import io.duryskuba.interestmatcher.AchievementService.util.AchievementConverter;
import io.duryskuba.interestmatcher.AchievementService.util.AchievementGroupConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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

    public AchievementGroupDTO createAchivementGroup(AchievementGroup achievementGroup) {
        return AchievementGroupConverter.toDto( achievementGroupRepository.save(achievementGroup) );
    }

    public void deleteAchievementGroup(Long achievementGroupId) {
        achievementGroupRepository.findById(achievementGroupId)
                    .ifPresent(achievementGroupRepository::delete);
    }

    public Collection<AchievementGroupDTO> findAllAchievementGroups() {
        return AchievementGroupConverter.toDtoCollection( achievementGroupRepository.findAll() );
    }

    public AchievementGroup findAchievementGroupByIdOrThrow(Long id) {
        return achievementGroupRepository.findById(id)
                    .orElseThrow(RuntimeException::new);
    }

    public Optional<AchievementGroup> findAchievementGroupById(Long id) {
        return achievementGroupRepository.findById(id);
    }

    public Collection<AchievementDTO> findAllAchievements() {
        return AchievementConverter.toDtoList( achievementRepository.findAll() );
    }

    public AchievementDTO findAchievementByIdOrThrow(Long id) {
        return AchievementConverter.toDto (
                    achievementRepository.findById(id)
                    .orElseThrow(RuntimeException::new) );
    }

    public Optional<Achievement> findAchievementById(Long id) {
        return achievementRepository.findById(id);
    }

    public AchievementDTO createAchievement(AchievementDTO achievementDTO) {
        return
                AchievementConverter.toDto (
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





    public void onAchievementAction(AchievementActionDTO action) {
        for (Achievement achievement:
                findAchievementGroupByIdOrThrow(action.getAchievementGroupId()).getAchievements())  {

           if( !loopOverAchievementsAndIncrementValue(achievement, action.getUserId()) )
               break;

        }
    }

    private boolean loopOverAchievementsAndIncrementValue(Achievement achievement,
                                                          Long userId) {
        return userAchievementRepository
                .findById( new UserAchievementId(achievement.getAchievementId(), userId) )
                .map(a -> incrementAchievementValue(a, achievement))
                .orElseGet(() -> initStateOfAchievement(achievement, userId));
    }

    private boolean incrementAchievementValue(UserAchievement userAchievement, Achievement achievement) {
        if ( AchievementGoalStatus.DONE.equals(userAchievement.getStatus()) )
            return true;
        else {
            userAchievement.setValue(userAchievement.getValue() + 1);
            if ( userAchievement.getValue().equals(achievement.getGoal()) ) {
                userAchievement.setStatus(AchievementGoalStatus.DONE);
            }
            return false;
        }
    }

    private boolean initStateOfAchievement(Achievement achievement, Long userId) {
        userAchievementRepository.save (
                initialInstance( new UserAchievementId(achievement.getAchievementId(), userId)) );
        return false;
    }

    private int getNextAchievementLevel(Achievement achievement) {
        return achievement
                .getAchievementGroup()
                    .getAchievements().size() + 1;
    }

    public Achievement setNextAvailableLevel(Achievement achievement) {
        achievement.setLevel( getNextAchievementLevel(achievement) );
        return achievement;
    }

//    public void todonameofmethodforincrementingachievement(AchievementActionDTO action) {
//        AchievementGroup group =
//                 findAchievementGroupById(action.getAchievementGroupId());
//
//        Set<Achievement> achievements = group.getAchievements();
//
//        final Long userId = action.getUserId();
//
//        for( Achievement a : achievements) {
//
//            Optional<UserAchievement> userAchievementOpt = userAchievementRepository
//                    .findById(new UserAchievementId(a.getAchievementId(), action.getUserId()));
//
//            if ( userAchievementOpt.isPresent() ) {
//
//                final UserAchievement userAchievement = userAchievementOpt.get();
//
//                if( AchievementGoalStatus.DONE.equals(userAchievement.getStatus()) )
//                    continue;
//                else {
//                    //increment value of achievement
//                    userAchievement.setValue( userAchievement.getValue() + 1);
//
//                    if( userAchievement.getValue().equals(a.getGoal()) ) {
//                        userAchievement.setStatus(AchievementGoalStatus.DONE);
//                    }
//
//                    break;
//                }
//
//            } else {
//                //init method for value of achievement
//                UserAchievement userAchievement
//                        = UserAchievement.builder()
//                            .userAchievementId(
//                                  new UserAchievementId(a.getAchievementId(), action.getUserId())
//                                )
//                            .status(AchievementGoalStatus.IN_PROGRESS)
//                            .value(1L)
//                            .build();
//
//                userAchievementRepository.save(userAchievement);
//                break;
//            }
//
//        }
//
//    }


}
