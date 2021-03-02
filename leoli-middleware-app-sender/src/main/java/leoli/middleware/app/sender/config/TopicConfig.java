package leoli.middleware.app.sender.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ Topic配置
 * 注意：
 * 利用Binding关系将Exchange、Routing Key、Queue形成对应关系
 * 此处Routing Key为Topic
 *
 * @author liyinan
 * @date 2020/4/13
 */
@Configuration
public class TopicConfig {

    /**
     * 交换器
     */
    private final static String EXCHANGE_SPRING = "leoli";
    /**
     * topic
     */
    private final static String TOPIC_SPRING_MESSAGE = "sender.001";
    private final static String TOPIC_SPRING_MESSAGES = "sender.002";
    private final static String TOPIC_SPRING_ALL = "sender.#";

    /**
     * 创建队列
     *
     * @return 方法同名队列Bean
     */
    @Bean
    public Queue queueMessage() {
        return new Queue(TOPIC_SPRING_MESSAGE);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(TOPIC_SPRING_MESSAGES);
    }

    /**
     * 将队列绑定到Topic交换器
     *
     * @return 方法同名交换器Bean
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_SPRING);
    }

    /**
     * 将队列绑定到Topic交换器，匹配单个topic
     *
     * @param queueMessage 同名队列{@link Bean}
     * @param exchange     交换器
     * @return
     */
    @Bean
    public Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(TOPIC_SPRING_MESSAGE);
    }

    /**
     * 将队列绑定到Topic交换器，采用#的方式
     * (#匹配0个或多个单词，*匹配一个单词)
     * 注意：
     * 这里将queueMessages这个队列绑定到了 spring.#这个topic和 springExchange这个交换器,
     * 订阅时指定订阅queueMessages这个队列即可。
     *
     * @param queueMessages 同名队列{@link Bean}
     * @param exchange      交换器
     * @return 绑定关系
     */
    @Bean
    public Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with(TOPIC_SPRING_ALL);
    }
}
