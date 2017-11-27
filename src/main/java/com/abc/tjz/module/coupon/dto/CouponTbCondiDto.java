package com.abc.tjz.module.coupon.dto;

import com.abc.tjz.entity.CouponTb;
import com.abc.tjz.util.dto.CondiDto;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author LiuTongbin
 * @date 2017/11/25 0025 10:39
 */
@Getter
@Setter
public class CouponTbCondiDto extends CondiDto<CouponTb> {

    private String categoryId;
    private String goodsName;

    @Override
    public Specification<CouponTb> toSpecification() {
        return (root, query, cb) -> {
           List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotBlank(getCategoryId())) {
                predicateList.add(cb.equal(root.get("category").get("id"), getCategoryId()));
            }
            if (StringUtils.isNotBlank(getGoodsName())) {
                predicateList.add(cb.like(root.get("goodsName"), "%" + getGoodsName().trim() + "%"));
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
    }
}
