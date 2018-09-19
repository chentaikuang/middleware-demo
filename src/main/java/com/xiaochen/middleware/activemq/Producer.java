package com.xiaochen.middleware.activemq;

import com.xiaochen.middleware.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableScheduling
public class Producer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue xcQueue;

    @Autowired
    private Topic xcTopic;

    @Scheduled(fixedDelay = 5000)
    public void sendDelay() {
//        printTestMsg();
        jmsTemplate.convertAndSend(xcQueue, getCurDate());
        jmsTemplate.convertAndSend(xcTopic, getCurDate());
    }

    private void printTestMsg() {
        TestMsg testMsg = new TestMsg(redisUtil.increment("msgIndex", 1).intValue());
        logger.error(testMsg.toString());
    }

    private String getCurDate() {
        return new SimpleDateFormat("yyyy-MM-dd hh:MM:ss").format(new Date());
    }
}
