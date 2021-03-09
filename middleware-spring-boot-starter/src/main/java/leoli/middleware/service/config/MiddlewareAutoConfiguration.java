package leoli.middleware.service.config;

import leoli.middleware.service.util.SpringContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务发布与订阅配置
 *
 * @author leoli
 * @date 2020/3/5
 */
@Configuration
@Import(MiddlewareProperties.class)
@ConditionalOnProperty(prefix = "leoli.middleware", name = "enable", havingValue = "true", matchIfMissing = false)
public class MiddlewareAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MiddlewareAutoConfiguration.class);

    @Autowired
    private MiddlewareProperties properties;

    // 打印配置文件中的leoli.middleware.enable的值
    @PostConstruct
    public void init() {
        LOGGER.debug("middleware-spring-boot-starter==>leoli.middleware.enable=[{}]", properties.isEnable());
        // 获取所有的producer和consumer
        List<String> services = new ArrayList<>();
        if (!CollectionUtils.isEmpty(properties.getProducer())) {
            services.addAll(properties.getProducer());
        }
        if (!CollectionUtils.isEmpty(properties.getConsumer())) {
            services.addAll(properties.getConsumer());
        }
        services.stream().distinct().forEach(this::registService);

    }

    /**
     * 规范：exchange-系统名;topic-服务编号;queue-系统名.服务编号.queue
     * 如：系统middleware注册的topic编号为0001，则serviceId为middleware.0001，queueId为middleware.0001.queue
     *
     * @param serviceId
     */
    private void registService(String serviceId) {
        try {
            String[] split = serviceId.split("\\.");
            String exchange = split[0];
            String topic = split[1];
            String queue = serviceId + ".queue";
            String exchangeBeanName = exchange + "Exchange";
            // 注册exchange
            SpringContextAware.registBean(exchangeBeanName, new TopicExchange(exchange));
            // 注册queue
            SpringContextAware.registBean(queue, new Queue(queue));
            // 通过topic绑定exchange和queue
            SpringContextAware.registBean("binding." + queue, BindingBuilder
                    .bind(SpringContextAware.getBean(queue, Queue.class))
                    .to(SpringContextAware.getBean(exchangeBeanName, TopicExchange.class))
                    .with(topic));
            LOGGER.info("服务注册成功==>[serviceId={}]", serviceId);
        } catch (Exception e) {
            LOGGER.warn("服务注册失败==>[serviceId={}]", serviceId, e);
        }
    }
}
