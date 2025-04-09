package com.csm.mybatis.test;

import com.csm.mybatis.binding.MapperProxyFactory;
import com.csm.mybatis.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author csm
 * @description
 * @create 2025/4/9 23:23
 */
public class ApiTest {
    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_MapperProxyFactory() {
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> sqlSession = new HashMap<>();
        //模拟sqlSession
        sqlSession.put("com.csm.mybatis.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户名称！ uId --> 10001");
        //创建代理类
        IUserDao userDao = factory.newInstance(sqlSession);
        //userDao.queryUserName
        String result = userDao.queryUserName("10001");
        logger.info("测试结果：{}", result);
    }
}
