package com.night.spring.proxy.dao.impl;

import com.night.spring.proxy.annation.ProxyAnnontion;
import com.night.spring.proxy.dao.Play;

/**
 * @author night
 * @version 1.1.0
 * @Date 2020/2/13 12:03
 */
public class PlayImpl2 implements Play {

    @Override
    public void getOut() {

        System.out.println("i want to get out2 ");
    }

    @ProxyAnnontion
    @Override
    public void stayHone() {

        System.out.println(" ok i stay hone2");
    }



    @Override
    public void shop(String car){

        System.out.println("购车2");
    }

    @Override
    public void eat(String fish, String meat){

        System.out.println("美食一条街2");
    }
}
