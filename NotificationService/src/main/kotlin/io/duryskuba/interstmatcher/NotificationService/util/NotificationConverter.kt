package io.duryskuba.interstmatcher.NotificationService.util

import io.duryskuba.interstmatcher.NotificationService.resource.Notification
import io.duryskuba.interstmatcher.NotificationService.resource.NotificationDTO
import io.duryskuba.interstmatcher.NotificationService.resource.NotificationStatus
import org.springframework.stereotype.Component

@Component
class NotificationConverter {


    fun toEntity(dto: NotificationDTO?)
            = Notification (
                    notificatedId = dto?.subscriber,
                    objectId = dto?.objectId,
                    content = dto?.content,
                    author = dto?.author,
                    status = NotificationStatus.ACTIVE,
                    url = dto?.url)


    fun toDTO(notification: Notification)
        = NotificationDTO (
            id = notification.id,
            objectId = notification.objectId,
            url = notification.url,
            content = notification.content,
            author = notification.author,
            status = notification.status,
            subscriber = notification.notificatedId)
}