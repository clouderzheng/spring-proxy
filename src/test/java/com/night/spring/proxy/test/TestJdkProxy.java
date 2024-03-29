package com.night.spring.proxy.test;

import com.night.spring.proxy.dao.Play;
import com.night.spring.proxy.dao.impl.PlayImpl;
import com.night.spring.proxy.dao.impl.PlayImpl2;
import org.junit.Before;
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
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");


        Play play = new PlayImpl2();

//        Runnable runnable = () -> System.out.println("1");
//        Play proxy = (Play) Proxy.newProxyInstance(play.getClass().getClassLoader(), play.getClass().getInterfaces(), new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//
//
//                //这里反射的是接口 获取自定义注解必须加在接口上
//                Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
//                Arrays.stream(declaredAnnotations).forEach(annotation -> System.out.println(annotation.annotationType()));
//                Object result = method.invoke(play, args);
//                System.out.println(" please wait ");
//                return result;
//            }
//        });
//        proxy.stayHone();


        Play playProxy = (Play) Proxy.newProxyInstance(play.getClass().getClassLoader(), play.getClass().getInterfaces(),(proxy, method, args) -> {
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            Arrays.stream(declaredAnnotations).forEach(annotation -> System.out.println(annotation.annotationType()));
            Object result = method.invoke(play, args);
            System.out.println(" please wait ");
            return result;
        });
        playProxy.stayHone();

//        proxy.getOut();
    }


    public static void main(String[] param) {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");


        Play play = new PlayImpl();



        Play playProxy = (Play) Proxy.newProxyInstance(play.getClass().getClassLoader(), play.getClass().getInterfaces(),(proxy, method, args) -> {
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            Arrays.stream(declaredAnnotations).forEach(annotation -> System.out.println(annotation.annotationType()));
            Object result = method.invoke(play, args);
            System.out.println(" please wait ");
            return result;
        });
        playProxy.stayHone();
    }



    @Test
    public void testMutilIntroduction(){
        Play play = new PlayImpl();

        Class<?> proxyClass = Proxy.getProxyClass(play.getClass().getClassLoader(), play.getClass().getInterfaces());
//        proxyClass.se

    }

}
