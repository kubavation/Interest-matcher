package io.duryskuba.interestmatcher.AchievementService.controller;

import io.duryskuba.interestmatcher.AchievementService.resource.Achievement;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementGroup;
import io.duryskuba.interestmatcher.AchievementService.service.AchievementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AchievementController {

    private AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping("/achievements")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(achievementService.findAllAchievements(), HttpStatus.OK);
    }

    @GetMapping("/achievements/{id}")
    public ResponseEntity<Achievement> findById(@PathVariable Long id) {
        return new ResponseEntity<>(achievementService.findAchievementById(id), HttpStatus.OK);
    }

    @PostMapping("/achievement-groups/{groupId}/achievements")
    public ResponseEntity<Achievement> create(@RequestBody Achievement achievement,
                                              @PathVariable Long groupId) {
        return new ResponseEntity<>(achievementService.createAchievement(achievement, groupId), HttpStatus.CREATED);
    }

    //lub achievementDTO bedzie posiadalo groupId // todo refactor

    @DeleteMapping("/achievements/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        achievementService.deleteAchievement(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
