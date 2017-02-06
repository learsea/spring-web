package com.csy.webmvc.rabbitmq;

/**
 * @author csy ,created on 2016/6/3.
 */
public class pushOrderListener {
    public void handle(String msg) {
        System.out.println(msg);
    }
}
