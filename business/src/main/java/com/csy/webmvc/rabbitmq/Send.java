package com.csy.webmvc.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author csy ,created on 2016/6/3.
 */
@Controller
@RequestMapping("rabbitmq")
public class Send {
    @Autowired
    @Qualifier("pushOrderTemplate")
    RabbitTemplate rabbitTemplate;

    @RequestMapping("test")
    @ResponseBody
    public String test(@RequestParam String msg) {
        rabbitTemplate.send("pushOrder", new Message(msg.getBytes(), MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build()));
        return "success";
    }
}
