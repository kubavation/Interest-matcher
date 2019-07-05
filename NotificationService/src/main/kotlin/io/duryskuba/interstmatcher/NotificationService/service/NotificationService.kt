package io.duryskuba.interstmatcher.NotificationService.service

import io.duryskuba.interstmatcher.NotificationService.repository.NotifcationRepository
import io.duryskuba.interstmatcher.NotificationService.resource.Notification
import io.duryskuba.interstmatcher.NotificationService.resource.NotificationDTO
import io.duryskuba.interstmatcher.NotificationService.resource.NotificationType
import org.springframework.stereotype.Service

@Service
class NotificationService(val notificationRepository: NotifcationRepository) {


    fun findAllByUser(id: Long) = notificationRepository.findAllByNotificatedId(id)





    fun createNotification(notificationDTO: NotificationDTO)
            = notificationDTO.type?.al?.apply(this, notificationDTO)



    fun createPostNotification(notificationDTO: NotificationDTO?): String { //todo change to notificationDTO

        var resultContent = ""
        notificationDTO?.tags?.forEach{ resultContent += "#" + it.name }

        resultContent += " ${notificationDTO?.content}"

        if(resultContent.length > 60)
            resultContent = resultContent.substring(0,60)

        return buildContent(notificationDTO?.author,"$resultContent...")
    }

    fun createInvitationNotification(notificationDTO: NotificationDTO): String { //todo change to notificationDTO
        //todo
        return "Użytkownik ${notificationDTO.author} zaprosił Cię do grupy ";
    }

    private fun buildContent(author: String?, content: String?)
                = "Użytkownik $author uzył tagów: $content"


}
