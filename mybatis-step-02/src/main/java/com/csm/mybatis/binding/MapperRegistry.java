package com.csm.mybatis.binding;

import cn.hutool.core.lang.ClassScanner;
import com.csm.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author csm
 * @description Mapper注册类, 扫描包路径
 * @create 2025/4/10 00:26
 */
public class MapperRegistry {

    /**
     * 将已添加的映射器代理加入到HashMap中
     */
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    /**
     * 注意这里返回的映射器代理工厂创建的对象
     */
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        // 获取映射器工厂 MapperProxyFactory
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        // 判断映射器代理工厂是否为空
        if (null == mapperProxyFactory) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }
        try {
            //创建接口对象
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause:" + e, e);
        }
    }

    //

    /**
     * 注册机，将对应接口类型的映射器代理工厂进行注册
     * @param type
     * @param <T>
     */
    public <T> void addMapper(Class<T> type) {
        /**
         * Mapper 必须是接口类型的才会注册
         */
        if (type.isInterface()) {
            if (hasMapper(type)) {
                // 重复注册会抛出异常
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }
            //注册映射器代理工厂
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    /**
     * 判断是否重复注册
     * @param type
     * @return
     * @param <T>
     */
    private <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    /**
     * 将包路径下的所有接口映射器代理工厂进行注册
     * @param packageName 包名
     */
    public void addMappers(String packageName) {
        //扫描包路径下的所有接口
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        //将所有接口的映射器代理工厂进行注册
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }
}
