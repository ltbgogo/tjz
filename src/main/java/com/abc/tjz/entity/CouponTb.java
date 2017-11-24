package com.abc.tjz.entity;

import com.abc.tjz.util.db.entity.IdDateEntity;
import com.abc.tjz.util.db.entity.IdEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
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
     * 标题
     */
    @Column(length = 1000)
    private String title;
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
}
