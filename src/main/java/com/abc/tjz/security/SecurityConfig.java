package com.abc.tjz.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@ConditionalOnWebApplication
public class SecurityConfig {
	
    @Bean
    FilterRegistrationBean securityFilterRegistrationBean$custom() {  
        FilterRegistrationBean registration = new FilterRegistrationBean();  
        registration.addUrlPatterns("/action/*");  
        registration.setName("security-filter$custom");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE);
        registration.setFilter(new SecurityFilter());
        return registration;  
    }
}


