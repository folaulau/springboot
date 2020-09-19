package com.lovemesomecoding.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@EnableScheduling
public class ThreadPoolConfig implements AsyncConfigurer {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /*
     * corePoolSize parameter is the amount of core threads which will be instantiated and kept in the pool. If all core
     * threads are busy and more tasks are submitted, then the pool is allowed to grow up to a maximumPoolSize.
     */

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // initial number of threads
        executor.setCorePoolSize(50);

        // max number of threads
        executor.setMaxPoolSize(Integer.MAX_VALUE);

        // some tasks may be put into a queue to wait for their turn.
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("JMS-Thread-");
        executor.setAllowCoreThreadTimeOut(true);
        executor.setKeepAliveSeconds(60);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        return executor;

    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {

        return (ex, method, listOfObjects) -> {
            log.error("****** AsyncConfig - handleUncaughtException(...) ******");
            log.error("*  Class name: {}", method.getDeclaringClass());
            log.error("*  Method name - {}", method.getName());
            log.error("*  Exception message - {}", ex.getMessage());

            for (Object param : listOfObjects) {
                log.error("*  Param - {}", param);
            }
            log.error("*************************************");
        };
    }

}
