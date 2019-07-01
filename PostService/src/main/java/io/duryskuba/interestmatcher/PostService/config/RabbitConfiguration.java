package io.duryskuba.interestmatcher.PostService.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    //@Value("${queue.tag_notification}")
    private String tagNotificationQueue = "test";
    //@Value("${fanout.tag_notification}")
    private String tagNotificationExchange = "test";


    @Bean
    Queue notificationQueue() {
        return new Queue(tagNotificationQueue, true);
    }

    @Bean
    FanoutExchange tagNotificationExchange() {
        return new FanoutExchange(tagNotificationExchange);
    }

    @Bean
    Binding tagNotificationBinding(@Qualifier("notificationQueue") Queue queue,
                                   @Qualifier("tagNotificationExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
