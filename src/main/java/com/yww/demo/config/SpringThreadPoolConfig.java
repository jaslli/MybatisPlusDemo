package com.yww.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 *      线程池配置
 * </P>
 *
 * @author yww
 * @since 2023/5/28
 */
@EnableAsync
@Configuration
public class SpringThreadPoolConfig {

    @Bean("taskExecutor")
    public Executor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数等于系统核数--8核
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(availableProcessors);
        // 设置最大线程数
        executor.setMaxPoolSize(availableProcessors * 2);
        //配置队列大小
        executor.setQueueCapacity(1024);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(60);
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 设置默认线程名称
        executor.setThreadNamePrefix("yww-thread");
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //执行初始化
        executor.initialize();

        return executor;
    }

}
