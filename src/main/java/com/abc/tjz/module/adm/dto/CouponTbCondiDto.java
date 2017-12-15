package com.abc.tjz.module.adm.dto;

import com.abc.tjz.entity.CouponTb;
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
public class CouponTbCondiDto implements CondiDto<CouponTb> {

    private String categoryId;
    private String goodsName;

    @Override
    public Specification<CouponTb> toSpecification() {
        return (root, query, cb) -> {
           List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(getCategoryId())) {
                predicates.add(cb.equal(root.get("category").get("id"), getCategoryId()));
            }
            if (StringUtils.isNotBlank(getGoodsName())) {
                predicates.add(cb.like(root.get("goodsName"), "%" + getGoodsName().trim() + "%"));
            }
            predicates.add(cb.equal(root.get("isDeleted"), false));
            return cb.and(predicates.stream().toArray(Predicate[]::new));
        };
    }
}
