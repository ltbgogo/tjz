package com.abc.tjz.security;

import lombok.SneakyThrows;
import org.apache.commons.lang3.Validate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@ConditionalOnWebApplication
public class SecurityConfig {

    @Bean
    FilterRegistrationBean illegalFtlFilterRegistrationBean$custom() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.addUrlPatterns("*.ftl");
        registration.setName("ftl-filter$custom");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registration.setFilter(new OncePerRequestFilter() {
            @SneakyThrows
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
                response.setStatus(404);
            }
        });
        return registration;
    }
}


