package com.csm.mybatis.binding;

import com.csm.mybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author csm
 * @description 映射器代理类--->实现InvocationHandler,对代理对象进行增强
 * @create 2025/4/9 22:37
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {
    private static final Long serialVersionUID = -6424540398559729838L;
    // sqlSession
    private SqlSession sqlSession;
    // 被代理的接口
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //不对Object.class中声明的方法进行代理(什么equals、toString之类的)
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        if (null != args) {
            return sqlSession.selectOne(method.getName(), args);
        } else {
            return sqlSession.selectOne(method.getName());
        }

    }
}