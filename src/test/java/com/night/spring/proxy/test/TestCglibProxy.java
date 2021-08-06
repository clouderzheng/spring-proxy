package com.night.spring.proxy.test;

import com.night.spring.proxy.annation.ProxyAnnontion;
import com.night.spring.proxy.domain.Handler;
import com.night.spring.proxy.domain.RejectHandler;
import com.night.spring.proxy.domain.Student;
import com.night.spring.proxy.service.Write;
import org.junit.Test;
import org.springframework.asm.Type;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.proxy.*;

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

                if(declaredAnnotations.length == 0){
                    System.out.println("空空如也");
                }

                Object result = method.invoke(write, args);
                return result;
            }
        });
        // 创建代理对象
        Write proxy = (Write) enhancer.create();
        proxy.write();
        System.out.println(proxy);
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



    @Test
    public void testInterface(){

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RejectHandler.class);
        enhancer.setInterfaces(new Class[]{Handler.class});
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                System.out.println("intercept before");
                Object invoke = method.invoke(o, objects);
                System.out.println("intercept handler");
//                System.out.println("intercept before");
//                Object invoke = method.invoke(o, objects);
//                System.out.println("intercept handler");
                return invoke;
            }
        });

        Object o = enhancer.create();
//
//        if(o instanceof  Handler){
//            Handler handler = (Handler)o;
//            handler.handler();
//        }

        if(o instanceof  RejectHandler){
            RejectHandler rejectHandler = (RejectHandler)o;
            rejectHandler.reject();
        }

    }


    @Test
    public void testBeanMap(){
        Student student = new Student();
        student.setAge(28);
        student.setName("night");

        BeanMap beanMap = BeanMap.create(student);
        beanMap.forEach( (k,v) ->{

            System.out.println(v);
        });
        System.out.println(BeanMap.create(student));
    }


    static {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/fenlai/Desktop/work/github/spring-proxy/src/test/java");

    }

    @Test
    public void testInterfaceMaker(){

        Signature signature = new Signature("Action", Type.LONG_TYPE,new Type[]{Type.INT_TYPE,Type.DOUBLE_TYPE});
        InterfaceMaker interfaceMaker = new InterfaceMaker();
        interfaceMaker.add(signature,new Type[0]);
        Class aClass = interfaceMaker.create();



        System.out.println(aClass.getSimpleName());
        System.out.println(aClass.getMethods()[0].getName());
        Class<? extends InterfaceMaker> aClass1 = interfaceMaker.getClass();
    }
}
