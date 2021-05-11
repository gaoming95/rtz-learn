package com.gaoming.rtzrabbit.mq.rabbitmq.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @file: ServiceNameMessage
 * @author: gaoming
 * @date: 2021/05/11
 * @version: 1.0
 * @description:
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceNameMessage extends MqMessage implements Serializable {
    private static final long serialVersionUID = 1701599858768826466L;

    private Integer accountId;

    private Integer goodId;
}
