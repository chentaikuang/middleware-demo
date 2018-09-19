package com.xiaochen.middleware.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private AtomicInteger queueMsgNo = new AtomicInteger(0);
    private AtomicInteger topicMsgNo = new AtomicInteger(0);


    @JmsListener(destination = ActivemqCont.XIAOCHEN_QUEUE)
    public void receiveQueue(String mqMsg){
        logger.info(queueMsgNo.getAndIncrement()+".queue receive:{}",mqMsg);
    }

    @JmsListener(destination = ActivemqCont.XIAOCHEN_TOPIC,containerFactory = "jmsListenerContainerTopic")
    public void receiveTopic(String mqMsg){
        logger.info(topicMsgNo.getAndIncrement()+".topic receive:{}",mqMsg);
    }

    @JmsListener(destination = ActivemqCont.XIAOCHEN_TOPIC,containerFactory = "jmsListenerContainerTopic")
    public void receiveTopic(){
        Message msgObj = (Message) jmsTemplate.receive(ActivemqCont.XIAOCHEN_TOPIC);
        try {
            logger.warn("[TOPIC]"+msgObj.getJMSMessageID() + "," + msgObj.toString());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @JmsListener(destination = ActivemqCont.XIAOCHEN_QUEUE,containerFactory = "jmsListenerContainerQueue")
    public void receiveQueue(){
        Message msgObj = jmsTemplate.receive(ActivemqCont.XIAOCHEN_QUEUE);
        logger.warn("[QUEUE]"+msgObj.toString());
    }

}
