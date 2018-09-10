package com.kaveinga.utility;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class BeanUtils implements ApplicationContextAware {
	private static final BeanUtils INSTANCE = new BeanUtils();
    private static ApplicationContext applicationContext;
 
    private BeanUtils() {
    }
 

    public static void autowire(Object classToAutowire, Object... beansToAutowireInClass) {
        for (Object bean : beansToAutowireInClass) {
            if (bean == null) {
                applicationContext.getAutowireCapableBeanFactory().autowireBean(classToAutowire);
                return;
            }
        }
    }
 
    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) {
    	BeanUtils.applicationContext = applicationContext;
    }
 
    /**
     * @return the singleton instance.
     */
    public static BeanUtils getInstance() {
        return INSTANCE;
    }

}
