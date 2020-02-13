package com.night.spring.proxy.test;

import com.night.spring.proxy.dao.Play;
import com.night.spring.proxy.dao.impl.PlayImpl;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author night
 * @version 1.1.0
 * @Date 2020/2/13 12:05
 */
public class TestJdkProxy {

    @Test
    public void testJdkProxy(){

        Play play = new PlayImpl();

        Play proxy = (Play) Proxy.newProxyInstance(play.getClass().getClassLoader(), play.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


                //这里反射的是接口 获取自定义注解必须加在接口上
                Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                Arrays.stream(declaredAnnotations).forEach(annotation -> System.out.println(annotation.annotationType()));
                Object result = method.invoke(play, args);
                System.out.println(" please wait ");
                return result;
            }
        });

//        proxy.getOut();
        proxy.stayHone();
    }
}
