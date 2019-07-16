package io.duryskuba.interstmatcher.NotificationService.resource


data class NotificationDTO (
    val id: Long? = 0,
    val objectId: Long? = 0,
    val type: NotificationType? = null,
    val url: String? = "",
    val content: String? = "",
    val author: String? = "",
    val subscriber: Long? = 0,
    val tags: Collection<Tag>? = ArrayList(),
    val status: NotificationStatus? = null
)