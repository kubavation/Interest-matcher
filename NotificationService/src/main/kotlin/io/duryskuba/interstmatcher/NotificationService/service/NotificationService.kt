package io.duryskuba.interstmatcher.NotificationService.service

import io.duryskuba.interstmatcher.NotificationService.repository.NotificationRepository
import io.duryskuba.interstmatcher.NotificationService.resource.Notification
import io.duryskuba.interstmatcher.NotificationService.resource.NotificationDTO
import io.duryskuba.interstmatcher.NotificationService.resource.NotificationStatus
import io.duryskuba.interstmatcher.NotificationService.util.NotificationConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class NotificationService(val notificationRepository: NotificationRepository,
                          val notificationConverter: NotificationConverter) {

    @Value("\${urls.services.post-service}")
    private val postUrl: String = ""

    fun findAll() = notificationRepository.findAll()

    fun findAllByUser(id: Long) = notificationRepository.findAllByNotificatedId(id)



    fun createNotification(notificationDTO: NotificationDTO)
            = notificationDTO.type?.func?.apply(this, notificationDTO )


    fun createTagNotification(notificationDTO: NotificationDTO?): NotificationDTO {
        val notification
                = notificationConverter.toEntity( buildTagNotificationContent(notificationDTO) )
        val result = notificationRepository.save(notification)
        return notificationConverter.toDTO(result)
    }

    fun createInvitationNotification(notificationDTO: NotificationDTO): NotificationDTO {
        val content = "Użytkownik ${notificationDTO.author} zaprosił Cię do grupy "
        return NotificationDTO()
    }


    private fun buildTagNotificationContent(notificationDTO: NotificationDTO?): NotificationDTO {

        var resultContent = ""
        notificationDTO?.tags?.forEach{ resultContent += "#" + it.name }

        resultContent += " ${notificationDTO?.content}"

        if(resultContent.length > 60)
            resultContent = resultContent.substring(0,60)

        return NotificationDTO (
            objectId = notificationDTO?.objectId,
            type =  notificationDTO?.type,
            subscriber = notificationDTO?.subscriber,
            content = "Użytkownik ${notificationDTO?.author} uzył tagów: $resultContent...",
            status = notificationDTO?.status,
            url = notificationDTO?.url,
            author = notificationDTO?.author,
            tags = notificationDTO?.tags
        )
    }



}
