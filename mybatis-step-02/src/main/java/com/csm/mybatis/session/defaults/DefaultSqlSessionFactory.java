package com.csm.mybatis.session.defaults;

import com.csm.mybatis.binding.MapperRegistry;
import com.csm.mybatis.session.SqlSession;
import com.csm.mybatis.session.SqlSessionFactory;

/**
 * @author csm
 * @description 默认的 DefaultSqlSessionFactory
 * @create 2025/4/10 00:53
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
