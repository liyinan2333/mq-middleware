package leoli.middleware.service.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

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
    }

}
