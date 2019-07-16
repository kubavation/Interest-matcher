package io.duryskuba.interstmatcher.NotificationService.service

import io.duryskuba.interstmatcher.NotificationService.repository.NotificationRepository
import io.duryskuba.interstmatcher.NotificationService.resource.Notification
import io.duryskuba.interstmatcher.NotificationService.resource.NotificationDTO
import io.duryskuba.interstmatcher.NotificationService.resource.NotificationStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class NotificationService(val notificationRepository: NotificationRepository) {

    @Value("\${urls.services.post-service}")
    private val postUrl: String = ""

    fun findAll() = notificationRepository.findAll()

    fun findAllByUser(id: Long) = notificationRepository.findAllByNotificatedId(id)



    fun createNotification(notificationDTO: NotificationDTO)
            = notificationDTO.type?.func?.apply(this, notificationDTO)


    fun createTagNotification(notificationDTO: NotificationDTO?): NotificationDTO {

        var resultContent = ""
        notificationDTO?.tags?.forEach{ resultContent += "#" + it.name }

        resultContent += " ${notificationDTO?.content}"

        if(resultContent.length > 60)
            resultContent = resultContent.substring(0,60)


        //todo build notificationDTO
        val content =  buildTagNotificationContent(notificationDTO?.author,"$resultContent...")
        println(content)


        val notification = Notification(
            notificatedId = notificationDTO?.subscriber,
            objectId = notificationDTO?.objectId,
            content = content,
            status = NotificationStatus.ACTIVE,
            url = postUrl + notificationDTO?.objectId)

        val result = notificationRepository.save(notification)

        return NotificationDTO (
                id = result.id,
                objectId = result.objectId,
                url = result.url,
                content = result.content,
                status = result.status,
                subscriber = result.notificatedId
        )
    }

    fun createInvitationNotification(notificationDTO: NotificationDTO): NotificationDTO {
        //todo
        val content = "Użytkownik ${notificationDTO.author} zaprosił Cię do grupy "
        return NotificationDTO()
    }



    private fun buildTagNotificationContent(author: String?, content: String?)
                = "Użytkownik $author uzył tagów: $content"

    //private fun


}
