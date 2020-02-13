package com.night.spring.proxy.test;

import com.night.spring.proxy.annation.ProxyAnnontion;
import com.night.spring.proxy.service.Write;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author night
 * @version 1.1.0
 * @Date 2020/2/13 14:02
 */
public class TestCglibProxy {

    @Test
    public void testCglib(){

        Write write = new Write();
        // 创建cglib核心对象
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(write.getClass());
        // 设置回调
        enhancer.setCallback(new MethodInterceptor() {
            /**
             * 当你调用目标方法时，实质上是调用该方法
             * intercept四个参数：
             * proxy:代理对象
             * method:目标方法
             * args：目标方法的形参
             * methodProxy:代理方法
             */
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
                    throws Throwable {

                Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                Arrays.stream(declaredAnnotations).forEach(annotation -> {
                    if(annotation.annotationType().getSimpleName().equalsIgnoreCase("ProxyAnnontion")){
                        System.out.println("我来拦截了");
                    }
                });
                Object result = method.invoke(write, args);
                return result;
            }
        });
        // 创建代理对象
        Write proxy = (Write) enhancer.create();
        proxy.write();
//        proxy.draw();
    }


    @Test
    public void testGetAnnation(){
        Method[] methods = Write.class.getMethods();

        for (Method method: methods) {
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            for (Annotation an: declaredAnnotations) {
                System.out.println(an.annotationType());
            }
        }
    }
}
