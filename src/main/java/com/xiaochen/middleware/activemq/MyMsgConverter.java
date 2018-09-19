package com.xiaochen.middleware.activemq;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;

public class MyMsgConverter implements MessageConverter {

    /**
     * 把一个JMS Message转换成对应的Java对象
     * @param message
     * @param aClass
     * @return
     */
    @Override
    public Object fromMessage(Message<?> message, Class<?> aClass) {
        return null;
    }

    /**
     * 把一个Java对象转换成对应的JMS Message
     * @param o
     * @param messageHeaders
     * @return
     */
    @Override
    public Message<?> toMessage(Object o, MessageHeaders messageHeaders) {
        return null;
    }
}
