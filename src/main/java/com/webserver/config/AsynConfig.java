
package com.webserver.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class AsynConfig {
	
	@Bean(name="webserver-executor")
	public Executor setExcutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(50);
		threadPoolTaskExecutor.setMaxPoolSize(100);
		threadPoolTaskExecutor.setQueueCapacity(100);
		
		return threadPoolTaskExecutor;
	}
}
