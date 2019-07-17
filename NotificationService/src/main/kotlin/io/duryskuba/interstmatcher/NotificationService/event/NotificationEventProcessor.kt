package io.duryskuba.interstmatcher.NotificationService.event

import io.duryskuba.interstmatcher.NotificationService.resource.NotificationDTO
import io.duryskuba.interstmatcher.NotificationService.service.NotificationService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class NotificationEventProcessor(
        val rabbitTemplate: RabbitTemplate,
        val notificationService: NotificationService) {

    @Value("\${queues.tag-notification}")
    private val notificationQueue: String? = ""

    @RabbitListener(queues = ["tagNotificationQueue"])
    fun listenForTagNotification(notificationDTO: NotificationDTO) {
        println(notificationDTO)
        notificationService.createNotification(notificationDTO)
    }

}