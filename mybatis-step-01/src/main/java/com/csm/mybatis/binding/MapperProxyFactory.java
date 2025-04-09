package com.csm.mybatis.binding;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author csm
 * @description 映射器代理工厂
 * @create 2025/4/9 22:35
 */
public class MapperProxyFactory<T> {
    //需要给那个接口进行代理，就把哪个接口注入进来
    private final Class<T> mapperInterface;

    //构造器注入
    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    //通过反射创建代理对象
    public T newInstance(Map<String, String> sqlSession) {
        //创建代理类对象
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        //传入类加载器、被代理的接口、以及InvocationHandler的实现
        // ClassLoader loader,Class<?>[] interfaces,InvocationHandler h
        return (T)Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface},mapperProxy);
    }
}
