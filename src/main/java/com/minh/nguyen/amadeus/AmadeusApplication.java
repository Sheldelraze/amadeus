package com.minh.nguyen.amadeus;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication(scanBasePackages = {"com.minh.nguyen"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan({ "com.minh.nguyen.mapper" })
@EnableAspectJAutoProxy
@EnableAsync
@EnableScheduling
public class AmadeusApplication {
	public static void main(String[] args) {
       SpringApplication.run(AmadeusApplication.class, args);
	}

}
