package com.csm.mybatis.session.defaults;

import com.csm.mybatis.binding.MapperRegistry;
import com.csm.mybatis.session.SqlSession;

/**
 * @author csm
 * @description
 * @create 2025/4/10 00:52
 */
public class DefaultSqlSession implements SqlSession {
    //组合了映射器注册机

    /**
     * 映射器注册机
     */
    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你被代理了！" + "方法：" + statement + "入参：" + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
