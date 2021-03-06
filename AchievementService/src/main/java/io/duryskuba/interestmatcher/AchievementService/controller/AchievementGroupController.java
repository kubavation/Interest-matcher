package io.duryskuba.interestmatcher.AchievementService.controller;

import io.duryskuba.interestmatcher.AchievementService.resource.AchievementGroup;
import io.duryskuba.interestmatcher.AchievementService.resource.AchievementGroupDTO;
import io.duryskuba.interestmatcher.AchievementService.service.AchievementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AchievementGroupController {

    private AchievementService achievementService;

    public AchievementGroupController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping("/achievement-groups")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(achievementService.findAllAchievementGroups(), HttpStatus.OK);
    }

    @GetMapping("/achievement-groups/{id}")
    public ResponseEntity<AchievementGroupDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(achievementService.findAchievementGroupDtoById(id), HttpStatus.OK);
    }

    @PostMapping("/achievement-groups")
    public ResponseEntity<AchievementGroupDTO> create(@RequestBody AchievementGroup group) {
        return new ResponseEntity<>(achievementService.createAchievementGroup(group), HttpStatus.CREATED);
    }

    @DeleteMapping("/achievement-groups/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        achievementService.deleteAchievementGroup(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
