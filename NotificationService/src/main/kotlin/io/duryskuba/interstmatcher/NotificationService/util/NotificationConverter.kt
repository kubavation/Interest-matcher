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
                    status = NotificationStatus.ACTIVE,
                    url = dto?.url + dto?.objectId)


    fun toDTO(notification: Notification)
        = NotificationDTO (
            id = notification.id,
            objectId = notification.objectId,
            url = notification.url,
            content = notification.content,
            status = notification.status,
            subscriber = notification.notificatedId)
}