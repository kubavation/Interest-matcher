package io.duryskuba.interestmatcher.AchievementService.controller;

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

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(achievementService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AchievementGroup> findById(@PathVariable Long id) {
        return new ResponseEntity<>(achievementService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AchievementGroup> create(@RequestBody AchievementGroup group) {
        return new ResponseEntity<>(achievementService.createAchivementGroup(group), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        achievementService.deleteAchievementGroup(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
