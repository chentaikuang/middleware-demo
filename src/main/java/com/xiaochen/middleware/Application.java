package com.xiaochen.middleware;

import com.xiaochen.middleware.activemq.ActivemqCont;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.jms.Queue;
import javax.jms.Topic;

@SpringBootApplication
@EntityScan("com.xiaochen.middleware.entity")
@EnableJpaRepositories(basePackages={"com.xiaochen.middleware.dao"})
/*@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })*/
@ComponentScan(basePackages={"com.xiaochen.middleware.*"})
public class Application {

	@Bean
	public Queue xcQueue(){
		return new ActiveMQQueue(ActivemqCont.XIAOCHEN_QUEUE);
	}

	@Bean
	public Topic xcTopic(){
		return new ActiveMQTopic(ActivemqCont.XIAOCHEN_TOPIC);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
