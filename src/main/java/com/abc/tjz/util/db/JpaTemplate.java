package com.abc.tjz.util.db;

import com.abc.tjz.util.misc.In2OutProcessor;
import com.abc.tjz.util.misc.SpringManager;
import com.abc.tjz.util.misc.Val;
import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.TypedValue;
import org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory;
import org.hibernate.hql.spi.ParameterTranslations;
import org.hibernate.hql.spi.QueryTranslator;
import org.hibernate.hql.spi.QueryTranslatorFactory;
import org.hibernate.internal.QueryImpl;
import org.hibernate.jpa.HibernateQuery;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author LiuTongbin
 * @date 2017/12/2 0002 11:18
 */
@Transactional(propagation = Propagation.SUPPORTS)
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
public class JpaTemplate {

    @Transactional(propagation = Propagation.REQUIRED)
    public int updateBySql(String sql, Object...args) {
        return this.setParams(SpringManager.getEntityManager().createNativeQuery(sql), args).executeUpdate();
    }

    public <T> List<T> queryBySql(String sql, In2OutProcessor<Object[], String[], T> mapper, Object...args) {
        return this.buildNativeQuery(sql, mapper, args).getResultList();
    }

    public <T> Page<T> queryForPageBySql(String sql, In2OutProcessor<Object[], String[], T> mapper, int page, int size, Object...args) {
        List<T> content = this.queryForLimitBySql(sql, mapper, page, size, args);
        long total = ((Number) getSingleBySql(getCountString(sql), (values, aliases) -> values[0], args)).longValue();
        return new PageImpl<T>(content, new PageRequest(page, size), total);
    }

    public <T> List<T> queryForLimitBySql(String sql, In2OutProcessor<Object[], String[], T> mapper, int page, int size, Object...args) {
        Pageable pageable = new PageRequest(page, size);
        String limitSql = SpringManager.getBean(Dialect.class).getLimitString(sql, pageable.getOffset(), pageable.getPageSize());
        Object[] limitArgs = pageable.getOffset() == 0 ? ArrayUtils.add(args, pageable.getPageSize()) : ArrayUtils.addAll(args,  pageable.getOffset(), pageable.getPageSize());
        return this.queryBySql(limitSql, mapper, limitArgs);
    }

    public <T> T getSingleBySql(String sql, In2OutProcessor<Object[], String[], T> mapper, Object... args) {
        return (T) this.buildNativeQuery(sql, mapper, args).getSingleResult();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <T> T update(Function<EntityManager, T> function) {
        return function.apply(SpringManager.getEntityManager());
    }

    public <T> T query(Function<EntityManager, T> function) {
        return function.apply(SpringManager.getEntityManager());
    }

    private String getCountString(String sql) {
        return "select count(1) from (" + sql + ")";
    }

    private Query setParams(Query query, Object... args) {
        for (int i = 0; i < args.length; i++) {
            query.setParameter(i + 1, args[i]);
        }
        return query;
    }

    private Query buildNativeQuery(String sql, In2OutProcessor<Object[], String[], ?> mapper, Object...args) {
        Query query = SpringManager.getEntityManager().createNativeQuery(sql);
        this.setParams(query, args);
        query.unwrap(SQLQuery.class).setResultTransformer(new SimpleResultTransformer(mapper));
        return query;
    }

    public static JpaTemplate of() {
        return SpringManager.getBean(JpaTemplate.class);
    }
}
