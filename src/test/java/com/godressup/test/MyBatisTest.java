package com.godressup.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.godressup.config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class MyBatisTest {

	@Autowired
	private SqlSessionFactory sqlFactory;
	
	private static Logger logger = LoggerFactory.getLogger(DataSourceTest.class);

	@Test
	public void testFactory() throws Exception {
		try(SqlSession session = sqlFactory.openSession()) {
			logger.info("session.toString(): " + session.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
