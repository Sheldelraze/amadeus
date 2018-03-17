
package com.minh.nguyen.config.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;
import java.util.concurrent.Executor;

/**
 * @author Mr.Minh
 * @since 06/02/2018
 * Purpose: this class define many configuration for the system,
 * so I suggest you should not change it if you don't know what
 * you are doing
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @PostConstruct
    protected void init() {
        //allow session to be used in asynchronous functions
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    //for uplaod file
    @Bean
    public MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    //config upload file setting
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("60MB");
        factory.setMaxRequestSize("120MB");
        return factory.createMultipartConfig();
    }
    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserAuthentication();
    }

    @Bean
    public AccessDeniedHandler getAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * configure async setting here,
     * for example: maximum thread running at the same time,
     * maximum queue size,...
     * but first, please do some research to understand what they really do
     * and make sure your hardware can handle it properly.
     */
    @Bean(name = "taskExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(30);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Judge");
        executor.initialize();
        return new DelegatingSecurityContextAsyncTaskExecutor(executor);
    }

    //this allows system to throw excepion when no controller is found for
    //some specific url. Just don't touch this....
    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                dispatcherServlet);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return registration;
    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }

    //configure security settings. Again, don't change if you don't know what you are doing
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/course/create").authenticated()
                .antMatchers("/contest/create").authenticated()
                .antMatchers("/").permitAll()
                .antMatchers("/contest/all").permitAll()
                .antMatchers("/problem/info/all").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/ace/**").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/nicEdit/**").permitAll()
                .antMatchers("/contest/**").permitAll()
                .antMatchers("/course/**").permitAll()
                .antMatchers("/scss/**").permitAll()
                .antMatchers("/chat/**").permitAll()
                .antMatchers("/material/download/**").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").successHandler(new AuthenticationSuccessHandler()).permitAll()
                .and()
                .logout().clearAuthentication(true).logoutSuccessHandler(new LogoutSuccessHandler()).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true).permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(getAccessDeniedHandler());
    }

    //better not touch here too...
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(getUserDetailsService())
                .passwordEncoder(getBCryptPasswordEncoder());
    }
}

