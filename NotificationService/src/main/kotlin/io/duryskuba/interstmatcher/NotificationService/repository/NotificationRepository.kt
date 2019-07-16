package io.duryskuba.interstmatcher.NotificationService.repository

import io.duryskuba.interstmatcher.NotificationService.resource.Notification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface NotificationRepository : JpaRepository<Notification, Long> {

    fun findAllByNotificatedId(notificatedId: Long): List<Notification>
//
//    @Query("from Notification n where n.userId = :userId and n.status = :status")
//    fun findByUserIdWhereStatus(@Param("userId") userId: Long, @Param("status") status: String): List<Notification>

    fun findAllByNotificatedIdAndStatus(notificatedId: Long, status: String): List<Notification>
}