package com.gaoming.rtzrabbit.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @file: ServiceNameConfig
 * @author: gaoming
 * @date: 2021/05/11
 * @version: 1.0
 * @description:
 **/
@Configuration
public class ServiceNameConfig {
    @Value("${rabbitmq.queue.service_name:queue_service_name}")
    private String serviceNameQueueName;

    @Value("${rabbitmq.exchange.service_name:exchange_service_name}")
    private String serviceNameExchangeName;

    @Bean
    public Queue serviceNameQueue() {
        return new Queue(serviceNameQueueName);
    }

    @Bean
    public FanoutExchange serviceNameExchange() {
        return new FanoutExchange(serviceNameExchangeName, true, false);
    }

    @Bean
    public Binding serviceNameMqBinding(@Qualifier("serviceNameQueue") Queue queue,
                                        @Qualifier("serviceNameExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);

        // return BindingBuilder.bind(queue).to(exchange).with(RoutingKey);
    }
}
