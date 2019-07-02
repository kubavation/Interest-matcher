package io.duryskuba.interestmatcher.TagService.event;

import io.duryskuba.interestmatcher.TagService.resource.PostDTO;
import io.duryskuba.interestmatcher.TagService.resource.TagSubscriber;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class EventProcessor {

   // public void emitNotificationEvent()


    @Async
    public void produceNotificationEvent(Collection<TagSubscriber> tags, final PostDTO post) {

//
//        //Set<User> notified = new HashSet<>();
//        Map<Long,HashSet<Tag>> notified = new HashMap<>();
//
//        tags.stream().forEach(t -> {
//            List<TagUser> tagUsers = tagUserService.findByTagName(t.getName());
//            tagUsers.stream().forEach(tu -> {
//
//                //obiekt z postId, userId ( biore z posta)// !! ALBO NIE ID, A CALE OBIEKTY!!!!! wtedy bedzie bez lipy
//                // jeszcze trzeba wziac pod uwage ze w danym poscie moze byc kilka tagow! zeby nie bylo 2 tych samych powiadomien
//                //rabbitTemplate.convertAndSend(messagingUtils.createMessage(tu.getId())); //todo chyba tez gdzies trzeba stworzyc link do posta czy cos
//
//                if(!notified.containsKey(tu.getId().getUserId()))
//                    notified.put(tu.getId().getUserId(), new HashSet<>(Arrays.asList(t)));
//                else
//                    notified.get(tu.getId().getUserId()).add(t);
//
//               // notified.add(new User(tu.getId().getUserId()));
//                //rabbitTemplate.convertAndSend(messagingUtils.createMessage(prepareNotification(post,tu)));
//            });
//        });

        /**
         * jakbym chcial przesylac jakie tagi zostaly przeslane to musze uzyc mapy
         */
        notified.entrySet()
                .forEach(e -> {
        rabbitTemplate.convertAndSend(messagingUtils
                .createMessage(prepareNotification(post,e.getKey(),e.getValue())));
    });

    //chyba gdzies tu trzeba laczyc wyniki niestety, poniewaz ludzie beda dostawac
    //powiadomienia 2 razy
    // a wiec ladowac wynik w set i dopiero wysylac event
}


}
