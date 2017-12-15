package com.abc.tjz.config.commonConfig;

import com.abc.tjz.repository.RepoFactory;
import com.abc.tjz.util.db.repository.DefaultRepositoryFactoryBean;
import com.abc.tjz.util.misc.SpringManager;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ClassUtils;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQLDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass = DefaultRepositoryFactoryBean.class,
	basePackageClasses = {RepoFactory.class})
public class DataJpaConfig {

	@SneakyThrows
	@Bean
	Dialect dialect$custom(EntityManagerFactory emf) {
		return (Dialect) Class.forName(emf.getProperties().get("hibernate.dialect").toString()).newInstance();
	}
}