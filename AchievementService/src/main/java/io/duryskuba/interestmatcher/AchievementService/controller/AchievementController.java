package io.duryskuba.interestmatcher.AchievementService.controller;

import io.duryskuba.interestmatcher.AchievementService.resource.Achievement;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementActionDTO;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementDTO;
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
    public ResponseEntity<AchievementDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(achievementService.findAchievementByIdOrThrow(id), HttpStatus.OK);
    }

    @PostMapping("/achievements")
    public ResponseEntity<AchievementDTO> create(@RequestBody AchievementDTO achievement) {
        return new ResponseEntity<>(achievementService.createAchievement(achievement), HttpStatus.CREATED);
    }

    @DeleteMapping("/achievements/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        achievementService.deleteAchievement(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/achievements/action")
    public ResponseEntity<Void> onAchievementAction(@RequestBody AchievementActionDTO achievementAction) {
        achievementService.onAchievementAction(achievementAction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/achievements/actionv2")
    public ResponseEntity<Void> onAchievementActionV2(@RequestBody AchievementActionDTO achievementAction) {
        achievementService.onAchievementActionv2(achievementAction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
