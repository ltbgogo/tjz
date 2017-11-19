package com.abc.tjz.config.webConfig;

import java.util.List;

import javax.servlet.Servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Web应用配置
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(ResourceProperties.class)
public class WebConfig {
	
    @Autowired(required = false)
    private List<ErrorViewResolver> errorViewResolvers;
    private final ServerProperties serverProperties;

    public WebConfig(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }
	
	/**
	 * WebMvcConfigurerAdapter
	 */
	@Bean
	WebMvcConfigurerAdapter webMvcConfigurerAdapter$custom() {
		return new DefaultWebMvcConfigurerAdapter();
	}

	/**
	 * DateConverter
	 */
	@Bean
	LocalDateTimeConverter localDateTimeConverter$custom() {
		return new LocalDateTimeConverter();
	}
	
	/**
	 * ErrorController
	 */
    @Bean
    ErrorController errorController$custom(ErrorAttributes errorAttributes) {
        return new ErrorController(errorAttributes, this.serverProperties.getError(), this.errorViewResolvers);
    }
    
    /**
	 * ServletContextInitializer
	 */
	@Bean
	ServletContextInitializer servletContextInitializer$bean() {
		return new DefaultServletContextInitializer();
	}

	/**
	 * Init filter
	 */
	@Bean
	FilterRegistrationBean initFilter$custom() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.addUrlPatterns("/action/*");
		registration.setName("action-filter$custom");
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
		registration.setFilter(new ActionFilter());
		return registration;
	}
}



