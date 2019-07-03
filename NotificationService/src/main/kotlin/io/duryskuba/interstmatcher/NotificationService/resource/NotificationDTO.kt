package io.duryskuba.interstmatcher.NotificationService.resource


data class NotificationDTO (
    val objectId: Long?,
    val type: NotificationType?,
    val content: String?,
    val author: String?,
    val subscriber: Long,
    val tags: Collection<Tag>?
)