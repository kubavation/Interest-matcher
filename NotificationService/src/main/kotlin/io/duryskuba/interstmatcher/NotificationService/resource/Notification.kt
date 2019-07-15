package io.duryskuba.interstmatcher.NotificationService.resource

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Notification (

        //todo change to inheritance
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val notificatedId: Long = 0,
    val url: String = "",
    val objectId: Long = 0,
    val content: String = "",
    val status: String = ""
)