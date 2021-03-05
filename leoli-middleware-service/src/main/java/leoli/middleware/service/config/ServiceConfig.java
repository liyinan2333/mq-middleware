package leoli.middleware.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 服务发布与订阅配置
 *
 * @author leoli
 * @date 2020/3/5
 */
@Component
@ConfigurationProperties(prefix = "leoli.service")
public class ServiceConfig {



}
