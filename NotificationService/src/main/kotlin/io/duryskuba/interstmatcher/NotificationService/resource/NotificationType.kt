package io.duryskuba.interstmatcher.NotificationService.resource

import io.duryskuba.interstmatcher.NotificationService.service.NotificationService
import org.springframework.beans.factory.annotation.Value
import java.util.function.BiFunction


enum class NotificationType(val func: BiFunction<NotificationService, NotificationDTO, NotificationDTO>) {

    POST(BiFunction<NotificationService, NotificationDTO, NotificationDTO> { service, dto
            -> service.createTagNotification(dto) }),

    INVITATION(BiFunction<NotificationService, NotificationDTO, NotificationDTO> { service, dto
        -> service.createInvitationNotification(dto) });


}
