package com.night.spring.proxy.annation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author night
 * @version 1.1.0
 * @Date 2020/2/13 12:16
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)  //反射获取到 必须加
public @interface ProxyAnnontion {
}
