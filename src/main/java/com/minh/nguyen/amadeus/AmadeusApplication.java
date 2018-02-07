package com.minh.nguyen.amadeus;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;
import java.util.concurrent.Executor;

@SpringBootApplication(scanBasePackages = {"com.minh.nguyen"})
@MapperScan({ "com.minh.nguyen.mapper" })
@EnableAsync

public class AmadeusApplication {
    private static Logger logger = LoggerFactory.getLogger(AmadeusApplication.class);
	public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(AmadeusApplication.class, args);
	}

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("Judge");
		executor.initialize();
		return executor;
	}
    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                dispatcherServlet);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return registration;
    }

}
