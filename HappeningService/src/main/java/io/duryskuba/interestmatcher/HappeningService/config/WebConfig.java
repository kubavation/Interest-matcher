package io.duryskuba.interestmatcher.HappeningService.config;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {

    @Bean
    WebClient webClient(LoadBalancerClient client) {
        return WebClient.builder()
                .filter(new LoadBalancerExchangeFilterFunction(client))
                .build();
    }
}
