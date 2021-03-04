package leoli.middleware.app.sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static leoli.middleware.app.sender.config.TopicConfig.EXCHANGE_MIDDLEWARE_TOPIC;
import static leoli.middleware.app.sender.config.TopicConfig.QUEUE_SENDER_0001;

/**
 * 测试topic
 *
 * @author leoli
 * @date 2021/3/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SenderTest {

    @Autowired
    private AmqpTemplate template;

    @Test
    public void send() {
        // 直接向队列中塞消息
        template.convertAndSend(QUEUE_SENDER_0001, "我是乘坐SENDER-0001过来的!");
        template.convertAndSend("SENDER-0001", new BigDecimal(12345.67890));
        // 通过exchange和topic(routingKey)找到对应的队列，由于队列spring.topic.all绑定了topic.#，所有topic.开头的消息都会塞到spring.topic.all队列中
        template.convertAndSend(EXCHANGE_MIDDLEWARE_TOPIC, "sender.000x", "sender广播呦～");
    }

}