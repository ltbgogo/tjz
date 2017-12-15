package com.abc.tjz.entity;

import com.abc.tjz.util.db.entity.IdDateEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

/**
 * 淘宝券
 *
 * @author LiuTongbin
 * @date 2017/11/19 0019 22:51
 */
@NoArgsConstructor
@Getter
@Setter
@Table(name = "t_coupon_tb")
@Entity
public class CouponTb extends IdDateEntity {

    /**
     * 图片路径
     */
    @Column(name = "image_path", length = 1000)
    private String imagePath;
    /**
     * 商品名称
     */
    @Column(length = 1000, name = "goods_name")
    private String goodsName;
    /**
     * 原始价格
     */
    @Column(name = "old_price")
    private Integer oldPrice;
    /**
     * 券价格
     */
    @Column(name = "coupon_price")
    private Integer couponPrice;
    /**
     * 券后价格
     */
    @Column(name = "buy_price")
    private Integer buyPrice;
    /**
     * 商品链接
     */
    @Column(length = 1000)
    private String link;
    /**
     * 购买人数
     */
    @Column(name = "buyer_count")
    @ColumnDefault("0")
    private Integer buyerCount;
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
}
