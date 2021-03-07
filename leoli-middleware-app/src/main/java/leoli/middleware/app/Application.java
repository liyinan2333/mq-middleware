package leoli.middleware.app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication(scanBasePackages = {"leoli.middleware"})
public class Application {

//    @Autowired
//    private AmqpTemplate template;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    }}
        // 直接向队列中塞消息
//        AmqpTemplate template = context.getBean(AmqpTemplate.class);
//        template.convertAndSend(TopicConfig.QUEUE_SENDER_0001, "我是乘坐SENDER-0001过来的!");
//        template.convertAndSend("SENDER-0001", new BigDecimal(12345.67890));
//        // 通过exchange和topic(routingKey)找到对应的队列，由于队列spring.topic.all绑定了topic.#，所有topic.开头的消息都会塞到spring.topic.all队列中
//        template.convertAndSend(TopicConfig.EXCHANGE_MIDDLEWARE_TOPIC, "sender.000x", "sender广播呦～");
//    }