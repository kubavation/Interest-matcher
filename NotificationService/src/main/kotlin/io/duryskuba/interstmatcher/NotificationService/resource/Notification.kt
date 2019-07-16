package io.duryskuba.interstmatcher.NotificationService.resource

import javax.persistence.*

@Entity
data class Notification (

    //todo change to inheritance
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val notificatedId: Long? = 0,
    val url: String? = "",
    val objectId: Long? = 0,
    val content: String? = "",
    @Enumerated(EnumType.STRING)
    val status: NotificationStatus?
)