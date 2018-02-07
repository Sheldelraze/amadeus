package com.minh.nguyen.config;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Mr.Minh
 * @since 06/02/2018
 * Purpose:
 */

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
public class WebConfig extends WebMvcAutoConfiguration {

}
