package com.night.spring.proxy.service;

import com.night.spring.proxy.annation.ProxyAnnontion;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author night
 * @version 1.1.0
 * @Date 2020/2/13 13:58
 */
public class Write {

    public void write (){
        System.out.println("i am writing");
    }

    @ProxyAnnontion
    public void draw(){

        System.out.println(" i am drawing ");
    }
}
