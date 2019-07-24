package io.duryskuba.interestmatcher.GroupService.controller;

import io.duryskuba.interestmatcher.GroupService.service.GroupService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    
}
