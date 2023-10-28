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

    @Test
    void testWorkQueue() throws InterruptedException {
        String queueName = "work.queue";
        for (int i = 1; i < 50; i++) {
            String msg = "hello, worker, message_" + i;
            rabbitTemplate.convertAndSend(queueName, msg);
            Thread.sleep(20);
        }
    }

    @Test
    void testSendFanout() {
        String exchangeName = "hmall.fanout";
        String msg = "hello, everyone!";
        rabbitTemplate.convertAndSend(exchangeName, null, msg);
    }

    @Test
    void testSendDirect() {
        String exchangeName = "hmall.direct";
        String msg = "蓝色通知, 警报解除, 哥斯拉是放的气球!";
        rabbitTemplate.convertAndSend(exchangeName, "blue", msg);
    }
}
