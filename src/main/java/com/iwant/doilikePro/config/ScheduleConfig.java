package com.iwant.doilikePro.config;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
/**
 * 
 * @author zkb
 *   定时器配置 定时器配合线程池
 *
 */
public class ScheduleConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));
    }
}
