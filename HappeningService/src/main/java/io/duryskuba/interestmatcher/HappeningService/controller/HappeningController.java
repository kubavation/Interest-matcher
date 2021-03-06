package io.duryskuba.interestmatcher.HappeningService.controller;

import io.duryskuba.interestmatcher.HappeningService.resource.Happening;
import io.duryskuba.interestmatcher.HappeningService.resource.HappeningDTO;
import io.duryskuba.interestmatcher.HappeningService.resource.HappeningParticipant;
import io.duryskuba.interestmatcher.HappeningService.resource.HappeningParticipantDTO;
import io.duryskuba.interestmatcher.HappeningService.service.HappeningService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController
public class HappeningController {

    private HappeningService happeningService;

    public HappeningController(HappeningService happeningService) {
        this.happeningService = happeningService;
    }


    @GetMapping("/happenings")
    public ResponseEntity<?> findById() {
        return new ResponseEntity<>(happeningService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/happenings/{id}")
    public ResponseEntity<Happening> findById(@PathVariable String id) {
        return new ResponseEntity<>(happeningService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/happenings")
    public ResponseEntity<Happening> create(@RequestBody HappeningDTO happening) {
        return new ResponseEntity<>(happeningService.create(happening), HttpStatus.CREATED);
    }

    @DeleteMapping("/happenings/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        happeningService.deleteHappeningById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/happenings/{happeningId}/participants")
    public ResponseEntity<HappeningParticipantDTO> addParticipant(@RequestBody HappeningParticipantDTO participant,
                                                                  @PathVariable String happeningId) {
        return new ResponseEntity<>(happeningService.addParticipantToHappening(participant), HttpStatus.CREATED);
    }

    @DeleteMapping("/happenings/{happeningId}/participants/{participantId}")
    public ResponseEntity<Void> removeParticipant(@PathVariable String happeningId,
                                                  @PathVariable String participantId) {
        happeningService.removeParticipantFromHappening(happeningId,participantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
