package com.gaoming.rtzrabbit.mq.rabbitmq.handler;

import com.gaoming.rtzrabbit.mq.rabbitmq.message.MqMessage;
import com.gaoming.rtzrabbit.mq.rabbitmq.message.ServiceNameMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @file: AbstractMqMessageHandler
 * @author: gaoming
 * @date: 2021/05/11
 * @version: 1.0
 * @description:
 **/
@Slf4j
public abstract class AbstractMqMessageHandler<T extends MqMessage> {

    @Autowired
    protected AmqpTemplate amqpTemplate;

    public void process(T msg) {
        log.info("try to process msg={}", msg);
        Boolean valid = messageValidate(msg);
        log.info("try to process msg with valid={}", valid);
        if (valid) {
            this.convertAndSend(msg);
        }
    }

    protected abstract void convertAndSend(T msg);

    protected abstract Boolean messageValidate(T msg);

    protected void sendMsgWithRetry(ServiceNameMessage msg, String name, String routingKey) {
        this.sendMsgWithRetry(msg, name, routingKey, 3);
    }

    private void sendMsgWithRetry(ServiceNameMessage msg, String exchangeName, String routingKey, Integer retryCount) {
        Boolean needRetry;
        Integer count = retryCount;
        Exception exp;

        do {
            try {
                log.info("try to send message with msg={}, exchangeName={}, routingKey={}, count={}", msg, exchangeName, routingKey, count);
                amqpTemplate.convertAndSend(exchangeName, routingKey, msg);
                exp = null;
                needRetry = false;
            } catch (Exception e) {
                needRetry = true;
                exp = e;
                log.warn("failed to send message with exception ", e);
            }
            count--;
        } while (needRetry && count > 0);

        if (exp != null) {
            throw new RuntimeException("send message failed with " + exp.getMessage());
        }
    }
}

