package com.minh.nguyen.amadeus;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication(scanBasePackages = {"com.minh.nguyen"},exclude = {SecurityAutoConfiguration.class })
@MapperScan({ "com.minh.nguyen.mapper" })
@EnableAsync
public class AmadeusApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmadeusApplication.class, args);
	}
	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("Judger");
		executor.initialize();
		return executor;
	}
}
