package leoli.middleware.app.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HandlerDemo implements MessageListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(HandlerDemo.class);
    private static final String serviceId = "sender.0001";

    @RabbitListener(queues = {serviceId + ".queue"})
    public void process(String msg) {
        LOGGER.info("Receive Message==>[serviceId={},body={}]", serviceId, msg);
    }

    @Override
    public void onMessage(Message message) {
        LOGGER.info("Receive Message==>[serviceId={},body={}]", serviceId, message);
    }
}
