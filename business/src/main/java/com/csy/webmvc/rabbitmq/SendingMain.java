package com.csy.webmvc.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author csy ,created on 2016/6/2.
 */
public class SendingMain {
    public static final String QUEUE_NAME = "pushOrder";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("rmq");
        factory.setPassword("123456");
        factory.setHost("172.16.20.242");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.confirmSelect();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        for (int i = 1; i <= 10; i++) {
            String message = "task" + i;
            for (int j = 1; j <= i; j++) {
                message += ".";
            }
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println("sent:" + message);
        }
        channel.close();
        connection.close();
    }
}
