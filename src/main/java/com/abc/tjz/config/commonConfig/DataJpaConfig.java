package com.abc.tjz.config.commonConfig;

import com.abc.tjz.repository.RepoFactory;
import com.abc.tjz.util.db.repository.DefaultRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass = DefaultRepositoryFactoryBean.class,
	basePackageClasses = {RepoFactory.class})
public class DataJpaConfig {

}