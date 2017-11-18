package com.abc.tjz.util.vo;

import org.springframework.data.jpa.domain.Specification;

public interface CondiVO<D> {

	Specification<D> toSpecification();
}
