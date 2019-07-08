package io.duryskuba.interestmatcher.HappeningService.controller;

import io.duryskuba.interestmatcher.HappeningService.resource.Happening;
import io.duryskuba.interestmatcher.HappeningService.resource.HappeningDTO;
import io.duryskuba.interestmatcher.HappeningService.service.HappeningService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public ResponseEntity<Happening> findById(@PathVariable UUID id) {
        return new ResponseEntity<>(happeningService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/happenings")
    public ResponseEntity<Happening> create(@RequestBody HappeningDTO happening) {
        return new ResponseEntity<>(happeningService.create(happening), HttpStatus.CREATED);
    }

}
