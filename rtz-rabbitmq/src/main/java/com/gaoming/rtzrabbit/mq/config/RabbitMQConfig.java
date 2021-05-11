package com.gaoming.rtzrabbit.mq.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @file: RabbitMQConfig
 * @author: gaoming
 * @date: 2021/05/11
 * @version: 1.0
 * @description:
 **/
@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.corePoolSize}")
    private Integer corePoolSize;
    @Value("${rabbitmq.maxPoolSize}")
    private Integer maxPoolSize;
    @Value("${rabbitmq.concurrency}")
    private String concurrency;
    @Value("${rabbitmq.queueCapacity}")
    private Integer queueCapacity;
    @Value("${rabbitmq.keepAliveSeconds}")
    private Integer keepAliveSeconds;

    @Bean(name = "mqExecutor")
    public ThreadPoolTaskExecutor mqExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        return executor;
    }

    @Bean
    public SimpleMessageListenerContainer ServiceNameListenerContainer(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter,
            @Qualifier("serviceNameQueue") Queue queue,
            @Qualifier("mqExecutor") Executor executor,
            @Qualifier("serviceNameListener") ChannelAwareMessageListener messageListener
    ){
        return defaultMessageListenerContainer(connectionFactory, messageConverter, queue, executor, messageListener);
    }

    private SimpleMessageListenerContainer defaultMessageListenerContainer(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter,
            Queue queue,
            Executor executor,
            ChannelAwareMessageListener messageListener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setTaskExecutor(executor);
        container.setConcurrency(concurrency);
//        container.setMessageConverter(messageConverter);
        container.setQueues(queue);
        container.setMessageListener(messageListener);
        return container;
    }
}
