package io.duryskuba.interstmatcher.NotificationService.event

import io.duryskuba.interstmatcher.NotificationService.resource.NotificationDTO
import io.duryskuba.interstmatcher.NotificationService.service.NotificationService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class NotificationEventProcessor(
        val rabbitTemplate: RabbitTemplate,
        val notificationService: NotificationService) {


    @RabbitListener(queues = arrayOf("notificationQueue"))
    fun listenAndNotify(notificationDTO: NotificationDTO) {
        print("here")
        println(notificationDTO.toString())
        println(notificationService.createPostNotification(notificationDTO.type, notificationDTO))
    }

}