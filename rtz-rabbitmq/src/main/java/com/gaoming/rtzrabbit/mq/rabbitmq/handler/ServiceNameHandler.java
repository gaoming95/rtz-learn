package com.gaoming.rtzrabbit.mq.rabbitmq.handler;

import com.gaoming.rtzrabbit.mq.rabbitmq.message.ServiceNameMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @file: ServiceNameHandler
 * @author: gaoming
 * @date: 2021/05/11
 * @version: 1.0
 * @description:
 **/
@Component
@Slf4j
public class ServiceNameHandler extends AbstractMqMessageHandler<ServiceNameMessage> {

    @Autowired
    @Qualifier("serviceNameExchange")
    private FanoutExchange exchange;

    @Override
    public void convertAndSend(ServiceNameMessage msg) {
        Assert.isTrue(messageValidate(msg), "非法消息不可发送");
        this.sendMsgWithRetry(msg, exchange.getName(), "s");

    }

    @Override
    public Boolean messageValidate(ServiceNameMessage msg) {
        log.info("messageValid with message={}", msg);
        return msg != null && msg.getAccountId() != null && msg.getGoodId() != null;
    }
}
