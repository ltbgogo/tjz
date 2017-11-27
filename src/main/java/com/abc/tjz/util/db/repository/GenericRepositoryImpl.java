package com.abc.tjz.util.db.repository;

import com.abc.tjz.util.dto.CondiDto;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

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
		query.where(root.get(ei.getIdAttribute()).in((Collection<ID>) ids));
		return em.createQuery(query).executeUpdate();
	}

	@Override
	public <R> R execute(BiFunction<EntityManager, JpaEntityInformation<D, ID>, R> function) {
		return function.apply(this.em, this.ei);
	}

	@Override
	public <R> Page<R> findAll(CondiDto<D> condi, Pageable pageable) {
		Specification<D> specification = (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(condi.toSpecification().toPredicate(root, query, cb));
			condi.getInterceptors().stream().forEach(i -> predicates.add(i.toPredicate(root, query, cb)));
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
		CriteriaBuilder cb = em.getCriteriaBuilder();
		long total = 0;
		{
			TypedQuery<Long> query = this.getCountQuery(specification, getDomainClass());
			total = query.getSingleResult();
		}
		if (total <= pageable.getOffset()) {
			return new PageImpl<R>(new ArrayList<R>(), pageable, total);
		} else {
			CriteriaQuery<D> query = cb.createQuery(ei.getJavaType());
			Root<D> root = query.from(ei.getJavaType());
			Predicate p = specification.toPredicate(root, query, cb);
			query.where(p);
			//设置排序
			query.orderBy(QueryUtils.toOrders(pageable.getSort(), root, cb));
			//设置分页
			TypedQuery<D> createQuery = em.createQuery(query);
			createQuery.setFirstResult(pageable.getOffset());
			createQuery.setMaxResults(pageable.getPageSize());
			//查询
			return new PageImpl<R>((List<R>) createQuery.getResultList(), pageable, total);
		}
	}

	@Override
	public <R> Page<R> findAll(CondiDto<D> condi, Pageable pageable, String... fields) {
		condi.getInterceptors().add((root, query, cb) -> {
			//query.select((Selection) cb.construct(CouponTakeout.class, (Selection<?>) root.get("id")));
			Selection[] selections = Arrays.stream(fields).map(root::get).toArray(Selection[]::new);
			query.multiselect(selections);
			return cb.and();
		});
		return this.findAll(condi, pageable);
	}
}







