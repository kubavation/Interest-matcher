package io.duryskuba.interstmatcher.NotificationService.resource

import io.duryskuba.interstmatcher.NotificationService.service.NotificationService
import java.util.function.BiFunction


enum class NotificationType(val al: BiFunction<NotificationService, NotificationDTO, String>) {

    POST(BiFunction<NotificationService, NotificationDTO, String> { service, dto
            -> service.createPostNotification(dto) }),

    INVITATION(BiFunction<NotificationService, NotificationDTO, String> { service, dto
        -> service.createInvitationNotification(dto) });


}
