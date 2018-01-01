package com.minh.nguyen.amadeus;


import com.minh.nguyen.Entity.CourseEntity;
import com.minh.nguyen.Mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.minh.nguyen"},exclude = {SecurityAutoConfiguration.class })
public class AmadeusApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmadeusApplication.class, args);
	}
}
