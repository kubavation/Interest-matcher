package io.duryskuba.interstmatcher.NotificationService.controller

import io.duryskuba.interstmatcher.NotificationService.service.NotificationService
import org.springframework.web.bind.annotation.RestController

@RestController
class NotificationController(val notificationService: NotificationService) {
}