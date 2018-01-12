
package com.minh.nguyen.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.access.AccessDeniedHandler;
//
////@Configuration
////@EnableWebSecurity
////@EnableGlobalMethodSecurity(securedEnabled=true)
////@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Configuration
public class MySecurityConfig extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySecurityConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/problem/**")
//                .addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/templates/");
    }

}

//    public SecurityConfig() {
//        super();
//    }
//
//    @Autowired
//    private AccessDeniedHandler accessDeniedHandler;
//
////    @Autowired
////    private UserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http.
//                authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/register").permitAll()
//                .antMatchers("/save").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login").defaultSuccessUrl("/getLoginPage").permitAll()
//                .and()
//                .logout().permitAll()
//                .and()
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
//
//    }
//
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
////    @Value("${spring.queries.users-query}")
////    private String usersQuery;
////
////    @Value("${spring.queries.roles-query}")
////    private String rolesQuery;
//
////    @Autowired
////    private DataSource getDataSource;
//
//    //    @Autowired
//////    private CustomAuthenticationProvider authProvider;
//
///*
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.
//                userDetailsService(userDetailsService)
//                //-----
////                authenticationProvider(authProvider);
//                //-----
////                jdbcAuthentication()
////                .usersByUsernameQuery(usersQuery)
////                .authoritiesByUsernameQuery(rolesQuery)
////                .dataSource(getDataSource)
//                .passwordEncoder(bCryptPasswordEncoder);
//        //
//    }
//*/
//
//}