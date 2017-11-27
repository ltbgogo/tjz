package com.abc.tjz.util.db.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class DefaultRepositoryFactoryBean<D extends JpaRepository<S, ID>, S, ID extends Serializable> extends JpaRepositoryFactoryBean<D, S, ID> {

	public DefaultRepositoryFactoryBean(Class<? extends D> repositoryInterface) {
		super(repositoryInterface);
	}

	protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
    	return new DefaultRepositoryFactory(em);
    }
}