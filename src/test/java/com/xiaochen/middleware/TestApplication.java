package com.xiaochen.middleware;

import com.xiaochen.middleware.dao.UserInfoDao;
import com.xiaochen.middleware.entity.UserInfo;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Queue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserInfoDao userInfoDao;

	@Test
	public void getUserInfo() {
		UserInfo userInfo = userInfoDao.findById(1);
		logger.info("==>>:{}",userInfo);
	}

	@Test
	public void getTotal() {
		long count = userInfoDao.count();
		logger.info("count:{}",count);
	}

}
