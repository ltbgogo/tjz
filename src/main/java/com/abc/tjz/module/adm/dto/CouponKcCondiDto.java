package com.abc.tjz.module.adm.dto;

import com.abc.tjz.entity.CouponKc;
import com.abc.tjz.util.dto.CondiDto;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LiuTongbin
 * @date 2017/11/25 0025 10:39
 */
@Getter
@Setter
public class CouponKcCondiDto implements CondiDto<CouponKc> {

    private String categoryId;
    private String couponName;

    @Override
    public Specification<CouponKc> toSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(getCategoryId())) {
                predicates.add(cb.equal(root.get("category").get("id"), getCategoryId()));
            }
            if (StringUtils.isNotBlank(getCouponName())) {
                predicates.add(cb.like(root.get("goodsName"), "%" + getCouponName().trim() + "%"));
            }
            predicates.add(cb.equal(root.get("isDeleted"), false));
            return cb.and(predicates.stream().toArray(Predicate[]::new));
        };
    }
}
