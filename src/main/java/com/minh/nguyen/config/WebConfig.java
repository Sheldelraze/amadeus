package com.minh.nguyen.config;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Mr.Minh
 * @since 06/02/2018
 * Purpose:
 */
@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcAutoConfiguration {

}
