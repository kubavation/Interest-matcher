package io.duryskuba.interstmatcher.NotificationService.controller

import io.duryskuba.interstmatcher.NotificationService.service.NotificationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class NotificationController(val notificationService: NotificationService) {


    @GetMapping("/notifications")
    fun findAll() = ResponseEntity(notificationService.findAll(), HttpStatus.OK);

    @GetMapping("/notifications/users/{id}")
    fun findAllByUser(@PathVariable id: Long) =
            ResponseEntity(notificationService.findAllByUser(id), HttpStatus.OK)



}