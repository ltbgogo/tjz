package com.abc.tjz.config.commonConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ImportResource({"classpath:/spring-tx.xml"})
@Configuration
public class CommonConfig {

	/**
	 * App
	 */
	@Bean
	App app$custom() {
		return App.INSTANCE;
	}
}



