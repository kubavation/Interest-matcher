package io.duryskuba.interestmatcher.TagService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    //@Value("${queue.tag_notification}")
    private String notificationQueue = "notificationQueue";
    //@Value("${fanout.tag_notification}")
    private String notificationExchange = "notificationExchange";


    @Bean
    Queue notficationQueue() {
        return new Queue(notificationQueue, true);
    }

    @Bean
    FanoutExchange notificationExchange() {
        return new FanoutExchange(notificationExchange);
    }

    @Bean
    Binding notificationBinding(@Qualifier("notficationQueue") Queue queue,
                                   @Qualifier("notificationExchange") FanoutExchange exchange) {
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