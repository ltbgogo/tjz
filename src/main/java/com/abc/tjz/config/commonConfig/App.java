package com.abc.tjz.config.commonConfig;

import com.abc.tjz.util.misc.ThreadContext;
import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;

import java.time.Instant;

@Setter
@Getter
@ConfigurationProperties("app")
public class App {

    /**
     * 应用版本号
     */
    private String version = Instant.now().getEpochSecond() + "";
	/**
	 * 单例
	 */
	public final static App INSTANCE = new App();
    /*
     * 枚举访问器
     */
    private final TemplateHashModel enums = new BeansWrapperBuilder(Configuration.VERSION_2_3_25).build().getEnumModels();
    /**
     * 静态方法访问器
     */
    private final TemplateHashModel statics = new BeansWrapperBuilder(Configuration.VERSION_2_3_25).build().getStaticModels();
    /**
     * web上下文
     */
    public String contextPath;
    /**
     * 模板页面中用到的静态资源的访问路径
     */
    public String getPageResPath() {
        String uri = ThreadContext.getRequest().getRequestURL().toString();
        return uri.replaceFirst("^.+/action/(.+)/\\w+$", this.getContextPath() + "/static/page/$1");
    }
    /**
     * 共享的静态资源访问路径
     */
    public String getPublicResPath() {
        return this.getContextPath() + "/static/public";
    }
    /**
     * 服务访问路径
     */
    public String getActionPath() {
        return this.getContextPath() + "/action";
    }
    /**
     * 动态资源访问路径
     */
    public String getDynamicResPath() {
        return this.getContextPath() + "/static/dynamic";
    }
    /**
     * 动态资源存储位置
     */
    private String dynamicResLocation;
}



