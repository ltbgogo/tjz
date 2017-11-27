package com.abc.tjz.util.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class CondiDto<D> {

	@Getter
	private List<Specification<D>> interceptors = new ArrayList<>();

	public abstract Specification<D> toSpecification();
}
