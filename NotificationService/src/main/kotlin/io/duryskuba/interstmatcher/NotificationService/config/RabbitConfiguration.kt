package io.duryskuba.interstmatcher.NotificationService.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfiguration {

    //@Value("\${queue.notification}")
    private val notificationQueue: String? = "notificationQueue"
    //@Value("\${fanout.notification}")
    private val notificationExchange: String? = "notificationExchange"

    @Bean
    fun notificationQueue(): Queue = Queue(notificationQueue,true)

    @Bean
    fun notificationExchange(): FanoutExchange = FanoutExchange(notificationExchange)

    @Bean
    fun notificationBinding(queue: Queue,exchange: FanoutExchange): Binding
            = BindingBuilder.bind(queue).to(exchange)

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = producerJackson2MessageConverter()
        return rabbitTemplate
    }

    @Bean
    fun producerJackson2MessageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }

}