package com.abc.tjz.util.dto;

import org.springframework.data.jpa.domain.Specification;

public interface CondiDto<D> {

	Specification<D> toSpecification();
}
