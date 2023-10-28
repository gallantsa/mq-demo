package com.itheima.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class SpringAmqpTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    void testSendMessage2Queue() {
        String queueName = "simple.queue";
        String msg = "hello, amqp!";
        rabbitTemplate.convertAndSend(queueName, msg);
    }
}
