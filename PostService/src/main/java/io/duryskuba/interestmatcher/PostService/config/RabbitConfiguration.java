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

    @Value("${queues.post-creation}")
    private String postCreationQueue;
    @Value("${fanouts.post-creation}")
    private String postCreationExchange;


    @Bean
    Queue postCreationQueue() {
        return new Queue(postCreationQueue, true);
    }

    @Bean
    FanoutExchange postCreationExchange() {
        return new FanoutExchange(postCreationExchange);
    }

    @Bean
    Binding tagNotificationBinding(@Qualifier("postCreationQueue") Queue queue,
                                   @Qualifier("postCreationExchange") FanoutExchange exchange) {
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
