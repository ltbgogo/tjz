package com.abc.tjz.util.db.repository;

import java.io.Serializable;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.abc.tjz.util.dto.CondiDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Root;

@NoRepositoryBean
public interface GenericRepository<D, ID extends Serializable> extends JpaRepository<D, ID>, JpaSpecificationExecutor<D> {
	
	int deleteByIds(Iterable<ID> ids);
	
	int updateByIds(Iterable<ID> ids, String fieldName, Object newFieldValue);
	
	List<D> findByIds(Iterable<ID> ids);		

	<R> R execute(BiFunction<EntityManager, JpaEntityInformation<D, ID>, R> function);

	<R> Page<R> findAll(CondiDto<D> condi, Pageable pageable);

	<R> Page<R> findAll(CondiDto<D> condi, Pageable pageable, String...fields);
}











