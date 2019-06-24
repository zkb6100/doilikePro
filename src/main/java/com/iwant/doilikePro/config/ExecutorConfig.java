package com.iwant.doilikePro.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
/**
 * 
 * @author zkb
 *   线程池
 *
 */
public class ExecutorConfig {
	private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);
	
	/** Set the ThreadPoolExecutor's core pool size. */
	private int corePoolSize = 10;
	/** Set the ThreadPoolExecutor's maximum pool size. */
	private int maxPoolSize = 200;
	/** Set the capacity for the ThreadPoolExecutor's BlockingQueue. */
	private int queueCapacity = 100;
	
	
	/** Set the ThreadPoolExecutor's core pool size. */
	private int my_corePoolSize = 10;
	/** Set the ThreadPoolExecutor's maximum pool size. */
	private int my_maxPoolSize = 20;
	/** Set the capacity for the ThreadPoolExecutor's BlockingQueue. */
	private int my_queueCapacity = 10;
	
	@Bean
	public Executor asyncServiceExecutor() {
		logger.info("start asyncServiceExecutor");
		ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
		//配置 核心线程数
		executor.setCorePoolSize(corePoolSize);
		//配置最大线程数
		executor.setMaxPoolSize(maxPoolSize);
		//配置 列队大小
		executor.setQueueCapacity(queueCapacity);
		//配置线程池中的线程的名称前缀
		executor.setThreadNamePrefix("async-service-");
		
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		//初始化
		executor.initialize();
		return executor;
	}
	
	@Bean
	public Executor mySimpleAsync() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(my_corePoolSize);
		executor.setMaxPoolSize(my_maxPoolSize);
		executor.setQueueCapacity(my_queueCapacity);
		executor.setThreadNamePrefix("MySimpleExecutor-");
		executor.initialize();
		return executor;
	}
	
	
}
