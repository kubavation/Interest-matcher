package io.duryskuba.interstmatcher.NotificationService.controller

import io.duryskuba.interstmatcher.NotificationService.service.NotificationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class NotificationController(val notificationService: NotificationService) {

    @GetMapping("/notifications/users/{id}")
    fun findByUser(@PathVariable Long id) = notificationService.findByUser(id);
}