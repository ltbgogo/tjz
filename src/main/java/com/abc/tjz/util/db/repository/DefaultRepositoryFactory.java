package com.abc.tjz.util.db.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

public class DefaultRepositoryFactory extends JpaRepositoryFactory {
    
	public DefaultRepositoryFactory(EntityManager em) {
		super(em);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <D, ID extends Serializable> JpaRepository<?, ?> getTargetRepository(RepositoryMetadata metadata, EntityManager em) {
		return new GenericRepositoryImpl(metadata.getDomainType(), em);
	}
	
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
    	return GenericRepositoryImpl.class;
    }
}



