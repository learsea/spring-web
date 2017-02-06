package com.csy.webmvc.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author csy ,created on 2016/6/2.
 */
public class ReceivingMain {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("rmq");
        factory.setPassword("123456");
        factory.setHost("172.16.20.242");
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare(SendingMain.QUEUE_NAME, true, false, false, null);
        System.out.println("waiting for messages...");
        channel.basicConsume(SendingMain.QUEUE_NAME, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "utf-8");
                System.out.println("receive:" + message);
                for (char c : message.toCharArray()) {
                    if (c == '.') {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("Done");
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
