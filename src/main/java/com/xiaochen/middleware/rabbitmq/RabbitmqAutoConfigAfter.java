package com.xiaochen.middleware.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(RabbitmqConfig.class)
//@ConditionalOnBean(RabbitmqConfig.class)
public class RabbitmqAutoConfigAfter {

    private static final Logger logger = LoggerFactory.getLogger(RabbitmqAutoConfigAfter.class);

    @Bean
    public TopicExchange topicExchange(RabbitAdmin rabbitAdmin){
        TopicExchange topicExchange = new TopicExchange(RabbitMqEnum.Exchange.TOPIC.getCode());
        rabbitAdmin.declareExchange(topicExchange);
        logger.info("完成主题型交换机bean实例化,"+RabbitMqEnum.Exchange.TOPIC.getName());
        return topicExchange;
    }

    @Bean
    public DirectExchange testDirectExchange(RabbitAdmin rabbitAdmin){
        DirectExchange directExchange = new DirectExchange(RabbitMqEnum.Exchange.DIRECT.getCode());
        rabbitAdmin.declareExchange(directExchange);
        logger.info("完成直连型交换机bean实例化,"+RabbitMqEnum.Exchange.DIRECT.getName());
        return directExchange;
    }

    @Bean
    public Queue testQueue(RabbitAdmin rabbitAdmin){
        Queue queue = new Queue(RabbitMqEnum.QueueName.XIAOCHEN_QUEUE.getCode());
        rabbitAdmin.declareQueue(queue);
        logger.info("完成队列bean实例化,"+RabbitMqEnum.QueueName.XIAOCHEN_QUEUE.getName());
        return queue;
    }

    //topic 1
    @Bean
    Queue testQueueTopic1(RabbitAdmin rabbitAdmin){
        Queue queue = new Queue(RabbitMqEnum.QueueName.TEST_TOPIC1.getCode());
        rabbitAdmin.declareQueue(queue);
        logger.info("话题测试队列1实例化完成");
        return queue;
    }
    //topic 2
    @Bean
    Queue testQueueTopic2(RabbitAdmin rabbitAdmin){
        Queue queue = new Queue(RabbitMqEnum.QueueName.TEST_TOPIC2.getCode());
        rabbitAdmin.declareQueue(queue);
        logger.info("话题测试队列2实例化完成");
        return queue;
    }


    //在此处完成队列和交换机绑定
    @Bean
    Binding testBindingQueue(Queue testQueue, DirectExchange exchange, RabbitAdmin rabbitAdmin){
        Binding binding = BindingBuilder.bind(testQueue).to(exchange).with(RabbitMqEnum.BindQueue.BIND_QUEUE.getCode());
        rabbitAdmin.declareBinding(binding);
        logger.info("测试队列与直连型交换机绑定完成");
        return binding;
    }
    //topic binding1
    @Bean
    Binding bindingTopicTest1(Queue testQueueTopic1,TopicExchange exchange,RabbitAdmin rabbitAdmin){
        Binding binding = BindingBuilder.bind(testQueueTopic1).to(exchange).with(RabbitMqEnum.BindQueue.ROUTING_QUEUE1.getCode());
        rabbitAdmin.declareBinding(binding);
        logger.info("测试队列与话题交换机1绑定完成");
        return binding;
    }

    //topic binding2
    @Bean
    Binding testBindingTopic2(Queue testQueueTopic2,TopicExchange exchange,RabbitAdmin rabbitAdmin){
        Binding binding = BindingBuilder.bind(testQueueTopic2).to(exchange).with(RabbitMqEnum.BindQueue.ROUTING_QUEUE2.getCode());
        rabbitAdmin.declareBinding(binding);
        logger.info("测试队列与话题交换机2绑定完成");
        return binding;
    }

}
