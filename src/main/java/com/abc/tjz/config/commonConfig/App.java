package com.abc.tjz.config.commonConfig;
import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;

@Setter
@Getter
@ConfigurationProperties("app")
public class App {

	/*
	 * 单例
	 */
	public final static App INSTANCE = new App();
}



