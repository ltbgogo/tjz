package com.abc.tjz.entity;

import com.abc.tjz.util.db.entity.IdDateEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 出行券
 *
 * @author LiuTongbin
 * @date 2017/11/19 0019 22:51
 */
@NoArgsConstructor
@Getter
@Setter
@Table(name = "t_coupon_kc")
@Entity
public class CouponKc extends IdDateEntity {

    /**
     * 券名称
     */
    @Column(length = 500, name = "coupon_name")
    private String couponName;
    /**
     * 缩略图片路径
     */
    @Column(name = "thumb_image_path", length = 1000)
    private String thumbImagePath;
    /**
     * 详情图片路径
     */
    @Column(name = "detail_image_path", length = 1000)
    private String detailImagePath;
    /**
     * 券价格
     */
    @Column(name = "coupon_price")
    private Integer couponPrice;
    /**
     * 过期时间
     */
    @Column(name = "expire_time", length = 20)
    private String expireTime;
    /**
     * 券类别
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    /**
     * 是否删除
     */
    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private Boolean isDeleted;
    /**
     * 券子类别
     */
    @ManyToMany
    @JoinTable(name = "t_category_rel",
            joinColumns = {@JoinColumn(name = "rel_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")},
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Set<Category> subCategories = new LinkedHashSet<>();
}
