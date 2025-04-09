package com.csm.mybatis.test;

import com.csm.mybatis.binding.MapperRegistry;
import com.csm.mybatis.session.SqlSession;
import com.csm.mybatis.session.defaults.DefaultSqlSessionFactory;
import com.csm.mybatis.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author csm
 * @description
 * @create 2025/4/9 23:23
 */
public class ApiTest {
    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_MapperProxyFactory() {
        //1.扫描包注册Mapper
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMappers("com.csm.mybatis.test");
        //2.获取默认的sqlSession
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = defaultSqlSessionFactory.openSession();
        //3.获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        String userResult = userDao.queryUserName("xxxxxx");
        logger.info("测试结果：{}",userResult);
    }
}
