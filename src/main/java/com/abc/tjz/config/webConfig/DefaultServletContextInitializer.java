package com.abc.tjz.config.webConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.abc.tjz.config.commonConfig.App;
import org.springframework.boot.web.servlet.ServletContextInitializer;

public class DefaultServletContextInitializer implements ServletContextInitializer {
	 
	public void onStartup(ServletContext context) throws ServletException {
		context.setAttribute("app", App.INSTANCE);
	}
}
