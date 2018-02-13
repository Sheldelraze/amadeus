package com.minh.nguyen.config;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;

/**
 * @author Mr.Minh
 * @since 06/02/2018
 * Purpose:
 * DO NOT DELETE THIS CLASS, IT MAY LOOK EMPTY BUT IT IS REALLY IMPORTANT.
 * IT ALLOWS HANDLING 404, 403,..  (MORE ON CLASS AdviceController) AND SHOW NOTIFICATION WHEN SYSTEM HAS ERRORS.
 * ALSO IT ALLOWS USING ASPECTJ TO CHECK SPECIFIC AUTHORITIES AND OTHER COOL STUFFS.
 * WITHOUT THIS CLASS THE SYSTEM WILL NOT FUNCTION AS IT SHOULD (OR EVEN BROKE DOWN)
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
public class WebConfig extends WebMvcAutoConfiguration {

}
