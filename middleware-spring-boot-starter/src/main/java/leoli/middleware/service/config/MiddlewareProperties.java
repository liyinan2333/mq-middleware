package leoli.middleware.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author leoli
 * @date 2020/3/7
 */
@Data
@ConfigurationProperties(prefix = "leoli.middleware")
public class MiddlewareProperties {

    private boolean enable;

    private List<String> produce;

    private List<String> consume;

}

//leoli:
//        middleware:
//            produce:
//            -- middleware.0001
//            consume:
//            -- sender.0001
//            -- sender.0002
//            -- sender.all