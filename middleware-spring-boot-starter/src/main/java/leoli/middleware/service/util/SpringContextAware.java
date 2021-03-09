package leoli.middleware.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextAware implements ApplicationContextAware, BeanFactoryPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringContextAware.class);
    private static ApplicationContext applicationContext;
    private static ConfigurableListableBeanFactory beanFactory;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        LOGGER.debug("SpringContextAware成功注入ApplicationContext");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        beanFactory = configurableListableBeanFactory;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static BeanFactory getBeanFactory() {
        return beanFactory;
    }

    /**
     * 主动向Spring容器中注册bean
     *
     * @param name BeanName
     * @param obj  要注册的对象
     * @return 返回注册到容器中的bean对象
     */
    public static void registBean(String name, Object obj) {
        if (applicationContext.containsBean(name)) {
            Object bean = applicationContext.getBean(name);
            if (!bean.getClass().isAssignableFrom(obj.getClass())) {
                throw new RuntimeException("BeanName 已存在 " + name);
            }
        } else {
            beanFactory.registerSingleton(name, obj);
        }
    }

    /**
     * 从容器中获取bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return (T) applicationContext.getBean(name);
    }
}
