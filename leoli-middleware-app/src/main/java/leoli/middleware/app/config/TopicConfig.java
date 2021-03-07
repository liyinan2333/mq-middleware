//package leoli.middleware.app.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * RabbitMQ Topic配置
// * 注意：
// * 利用Binding关系将Exchange、Routing Key、Queue形成对应关系
// * 此处Routing Key为Topic
// *
// * @author liyinan
// * @date 2020/4/13
// */
//@Configuration
//public class TopicConfig {
//
//    /**
//     * 交换器
//     */
//    public final static String EXCHANGE_MIDDLEWARE_TOPIC = "leoli.middleware.topic";
//    /**
//     * topic
//     */
//    public final static String QUEUE_SENDER_0001 = "SENDER-0001";
//    public final static String QUEUE_SENDER_0002 = "SENDER-0002";
//    public final static String QUEUE_SENDER_ALL = "SENDER-ALL";
//
//    public final static String TOPIC_SENDER_0001 = "sender.0001";
//    public final static String TOPIC_SENDER_0002 = "sender.0002";
//    public final static String TOPIC_SENDER_ALL = "sender.#";
//
//    /**
//     * 创建队列
//     *
//     * @return 方法同名队列Bean
//     */
//    @Bean
//    public Queue queueSender0001() {
//        return new Queue(QUEUE_SENDER_0001);
//    }
//
//    @Bean
//    public Queue queueSender0002() {
//        return new Queue(QUEUE_SENDER_0002);
//    }
//
//    @Bean
//    public Queue queueSenderAll() {
//        return new Queue(QUEUE_SENDER_ALL);
//    }
//
//    /**
//     * 将队列绑定到Topic交换器
//     *
//     * @return 方法同名交换器Bean
//     */
//    @Bean
//    public TopicExchange exchange() {
//        return new TopicExchange(EXCHANGE_MIDDLEWARE_TOPIC);
//    }
//
//    /**
//     * 将队列绑定到Topic交换器，匹配单个topic
//     * queueSender0001这个队列，通过sender.0001这个topic，绑定到leoli这个交换机
//     *
//     * @param queueSender0001 同名队列{@link Bean}
//     * @param exchange        交换器
//     * @return
//     */
//    @Bean
//    public Binding bindingSender0001(Queue queueSender0001, TopicExchange exchange) {
//        return BindingBuilder.bind(queueSender0001).to(exchange).with(TOPIC_SENDER_0001);
//    }
//
//    /**
//     * 将队列绑定到Topic交换器，匹配单个topic
//     * queueSender0002这个队列，通过sender.0002这个topic，绑定到leoli这个交换机
//     *
//     * @param queueSender0002 同名队列{@link Bean}
//     * @param exchange        交换器
//     * @return
//     */
//    @Bean
//    public Binding bindingSender0002(Queue queueSender0002, TopicExchange exchange) {
//        return BindingBuilder.bind(queueSender0002).to(exchange).with(TOPIC_SENDER_0002);
//    }
//
//    /**
//     * 将队列绑定到Topic交换器，采用#的方式
//     * (#匹配0个或多个单词，*匹配一个单词)
//     * 注意：
//     * 这里将QUEUE_SENDER_ALL这个队列绑定到了 spring.#这个topic和 springExchange这个交换器,
//     * 订阅时指定订阅QUEUE_SENDER_ALL这个队列即可。
//     *
//     * @param queueSenderAll 同名队列{@link Bean}
//     * @param exchange       交换器
//     * @return 绑定关系
//     */
//    @Bean
//    public Binding bindingSenderAll(Queue queueSenderAll, TopicExchange exchange) {
//        return BindingBuilder.bind(queueSenderAll).to(exchange).with(TOPIC_SENDER_ALL);
//    }
//}
