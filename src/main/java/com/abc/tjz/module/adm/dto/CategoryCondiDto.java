package com.abc.tjz.module.adm.dto;

import com.abc.tjz.entity.Category;
import com.abc.tjz.util.dto.CondiDto;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author LiuTongbin
 * @date 2017/12/9 0009 22:31
 */
@Getter
@Setter
public class CategoryCondiDto implements CondiDto<Category> {

    private String parentId;

    @Override
    public Specification<Category> toSpecification() {
        return (r, q, c) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(getParentId())) {
                predicates.add(c.equal(r.get("parent").get("id"), getParentId()));
            }
            return c.and(predicates.stream().toArray(Predicate[]::new));
        };
    }
}
