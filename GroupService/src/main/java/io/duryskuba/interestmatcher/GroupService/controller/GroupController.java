package io.duryskuba.interestmatcher.GroupService.controller;

import io.duryskuba.interestmatcher.GroupService.resource.GroupDTO;
import io.duryskuba.interestmatcher.GroupService.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @GetMapping("/groups/{id}")
    public ResponseEntity<GroupDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(groupService.findGroupById(id), HttpStatus.OK);
    }

}
