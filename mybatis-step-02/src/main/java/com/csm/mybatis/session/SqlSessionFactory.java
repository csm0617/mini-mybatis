package com.csm.mybatis.session;

/**
 * @author csm
 * @description 工厂模式接口，构建SqlSession的工厂
 * @create 2025/4/10 00:30
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
