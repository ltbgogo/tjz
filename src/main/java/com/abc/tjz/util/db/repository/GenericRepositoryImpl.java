package com.abc.tjz.util.db.repository;

import com.abc.tjz.util.db.DBUtil;
import com.abc.tjz.util.db.ResultMapper;
import com.abc.tjz.util.db.SimpleResultTransformer;
import com.abc.tjz.util.db.entity.IdDateEntity;
import com.abc.tjz.util.misc.SpringManager;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.QueryDslUtils;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@NoRepositoryBean
public class GenericRepositoryImpl<D, ID extends Serializable> extends SimpleJpaRepository<D, ID> implements GenericRepository<D, ID> {

	private EntityManager em;
	private JpaEntityInformation<D, ID> ei;

	public GenericRepositoryImpl(JpaEntityInformation<D, ?> ei, EntityManager em) {
		super(ei, em);
		this.em = em;
		this.ei = (JpaEntityInformation<D, ID>) ei;
	}

	public GenericRepositoryImpl(Class<D> domainClass, EntityManager em) {
		this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
	}

	@Override
	public int deleteByIds(Iterable<ID> ids) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<D> query = cb.createCriteriaDelete(ei.getJavaType());
		Root<D> root = query.from(ei.getJavaType());
		query.where(root.get(this.ei.getIdAttribute()).in((Collection<ID>) ids));
		return em.createQuery(query).executeUpdate();
	}

	@Override
	public List<D> findByIds(Iterable<ID> ids) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<D> query = cb.createQuery(ei.getJavaType());
		Root<D> root = query.from(ei.getJavaType());
		query.where(root.get(ei.getIdAttribute()).in((Collection<ID>) ids));
		return em.createQuery(query).getResultList();
	}

	@Override
	public int updateByIds(Iterable<ID> ids, String fieldName, Object newFieldValue) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<D> query = cb.createCriteriaUpdate(ei.getJavaType());
		Root<D> root = query.from(ei.getJavaType());
		query.set(root.get(fieldName), newFieldValue);
		if (IdDateEntity.class.isAssignableFrom(ei.getJavaType())) {
			query.set(root.get("updateDateTime"), LocalDateTime.now());
		}
		query.where(root.get(ei.getIdAttribute()).in((Collection<ID>) ids));
		return em.createQuery(query).executeUpdate();
	}

	@Override
	public <R> R execute(BiFunction<EntityManager, JpaEntityInformation<D, ID>, R> function) {
		return function.apply(this.em, this.ei);
	}

	@Override
	public JSONObject findJsonObject(ID id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<?> query = cb.createTupleQuery();
		Root<D> root = query.from(ei.getJavaType());
		query.where(cb.equal(root.get(ei.getIdAttribute().getName()), id));
		query.multiselect(DBUtil.getSelections(root, ei.getJavaType(), true));
		TypedQuery<?> typedQuery = em.createQuery(query);
		final String[] realAliases = query.getSelection().getCompoundSelectionItems().stream().map(Selection::getAlias).toArray(String[]::new);
		typedQuery.unwrap(Query.class).setResultTransformer(new SimpleResultTransformer(ResultMapper.ofFastJson(realAliases)));
		return (JSONObject) typedQuery.getSingleResult();
	}

	@Override
	public Page<JSONObject> findJsonObjects(Specification<D> specification, Pageable pageable, String... fields) {
		specification = specification == null ? (r, q, c) -> c.conjunction() : specification;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		long total = 0;
		{
			TypedQuery<Long> query = this.getCountQuery(specification, getDomainClass());
			total = query.getSingleResult();
		}
		if (total <= pageable.getOffset()) {
			return new PageImpl<>(new ArrayList<JSONObject>(), pageable, total);
		} else {
			CriteriaQuery<?> query = cb.createTupleQuery();
			Root<D> root = query.from(ei.getJavaType());
			query.where(specification.toPredicate(root, query, cb));
			List<Selection<?>> selections = query.getSelection() == null ? new ArrayList<>() : new ArrayList<>(query.getSelection().getCompoundSelectionItems());
			selections.addAll(Arrays.stream(fields).map(f -> {
				Path<?> path = root;
				String alias = "";
				for (String sf : f.split("\\.")) {
					path = path.get(sf);
					alias += StringUtils.capitalize(sf);
				}
				path.alias(StringUtils.uncapitalize(alias));
				return path;
			}).collect(Collectors.toList()));
			if (selections.isEmpty()) {
				selections.addAll(DBUtil.getSelections(root, ei.getJavaType(), false));
			}
			query.multiselect(selections);
			SpringManager.getEntityManagerFactory().getProperties();
			//设置排序
			query.orderBy(QueryUtils.toOrders(pageable.getSort(), root, cb));
			//设置分页
			TypedQuery<?> typedQuery = em.createQuery(query);
			typedQuery.setFirstResult(pageable.getOffset());
			typedQuery.setMaxResults(pageable.getPageSize());
			final String[] realAliases = query.getSelection().getCompoundSelectionItems().stream().map(Selection::getAlias).toArray(String[]::new);
			typedQuery.unwrap(Query.class).setResultTransformer(new SimpleResultTransformer(ResultMapper.ofFastJson(realAliases)));
			//查询
			return new PageImpl<JSONObject>((List<JSONObject>) typedQuery.getResultList(), pageable, total);
		}
	}
}







