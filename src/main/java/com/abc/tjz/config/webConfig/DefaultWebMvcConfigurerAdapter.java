package com.abc.tjz.config.webConfig;

import com.abc.tjz.config.commonConfig.App;
import com.abc.tjz.util.json.JpaLazyIntrospector;
import com.abc.tjz.util.json.LocalDateTimeSerializer;
import com.abc.tjz.util.json.TextEnumSerializer;
import com.abc.tjz.util.spec.TextEnum;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class DefaultWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
	
	/**
	 * Controller映射
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//配置快捷方式
		registry.addRedirectViewController("/", "/action/quantb/showIndex").setKeepQueryParams(true);
	}
	
	/**
	 * 静态资源映射
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/static/page/**").addResourceLocations("classpath:templates/");
		registry.addResourceHandler("/static/dynamic/**").addResourceLocations(App.INSTANCE.getDynamicResLocation());
    }

	/**
	 * JSON消息处理
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(Date.class, new DateSerializer(false, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        simpleModule.addSerializer(TextEnum.class, new TextEnumSerializer());
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(simpleModule);
        objectMapper.setAnnotationIntrospector(new JpaLazyIntrospector());
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        objectMapper.setSerializationInclusion(Include.NON_EMPTY);
        
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        converters.add(0, converter);
	}
}
