package com.gaoming.rtzrabbit.mq.controller;

import com.gaoming.rtzrabbit.mq.rabbitmq.handler.ServiceNameHandler;
import com.gaoming.rtzrabbit.mq.rabbitmq.message.ServiceNameMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @file: IndexController
 * @author: gaoming
 * @date: 2021/05/11
 * @version: 1.0
 * @description:
 **/
@RestController
@RequestMapping(value = "index")
public class IndexController {
    @Autowired
    private ServiceNameHandler serviceNameHandler;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public void index() {
        System.out.println("index");
        for (int i = 0; i < 1000; i++) {
            serviceNameHandler.convertAndSend(ServiceNameMessage.builder()
                    .accountId(1)
                    .goodId(1)
                    .build());
        }
        System.out.println("send finish");
    }
}
