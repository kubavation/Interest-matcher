package io.duryskuba.interstmatcher.NotificationService.resource


data class NotificationDTO (
    val objectId: Long? = 0,
    val type: NotificationType? = null,
    val content: String? = "",
    val author: String? = "",
    val subscriber: Long = 0,
    val tags: Collection<Tag>? = ArrayList()
)