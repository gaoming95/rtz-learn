package com.gaoming.rtzrabbit.mq.rabbitmq.listener;

import com.gaoming.rtzrabbit.mq.rabbitmq.message.ServiceNameMessage;
import com.gaoming.rtzrabbit.mq.util.FastJsonMessageConverter;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @file: ServiceNameListener
 * @author: gaoming
 * @date: 2021/05/11
 * @version: 1.0
 * @description:
 **/
@Component
@Slf4j
public class ServiceNameListener implements ChannelAwareMessageListener {

    @Autowired
    private FastJsonMessageConverter messageConverter;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        log.info("do some service, message is {}", message);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String key = message.getMessageProperties().getReceivedRoutingKey();
        System.out.println(key);
        ServiceNameMessage msg;

        try {
            msg = messageConverter.fromMessage(message, ServiceNameMessage.class);
        } catch (Exception e) {
            log.error("ServiceName: message params error ", e);
            channel.basicReject(deliveryTag, false);
            return;
        }

        try {
            this.processServiceName(msg);
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("Service Name failed with msg={}, error is {}", msg, e);
            channel.basicReject(deliveryTag, false);
        }
    }

    private void processServiceName(ServiceNameMessage msg) {
        // 处理消息
        System.out.println("消息处理完成");
    }
}

