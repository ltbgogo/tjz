package com.abc.tjz.entity;

import com.abc.tjz.util.db.entity.IdDateEntity;
import com.abc.tjz.util.json.JpaEntityPropertyFilter;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

/**
 * @author LiuTongbin
 * @date 2017/11/19 0019 22:51
 */
@NoArgsConstructor
@Getter
@Setter
@Table(name = "t_coupon_cx")
@Entity
public class CouponCx extends IdDateEntity {

    /**
     * 券名称
     */
    @Column(length = 500, name = "coupon_name")
    private String couponName;
    /**
     * 提示
     */
    @Column(length = 500, name = "tip")
    private String tip;
    /**
     * 券链接
     */
    @Column(length = 1000)
    private String link;
    /**
     * 阅读人数
     */
    @Column(name = "pageviews")
    @ColumnDefault("0")
    private Integer pageviews;
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
     * 券类别
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    /**
     * 开始日期
     */
    @Column(name = "start_time", length = 20)
    private String startTime;
    /**
     * 优惠简介
     */
    @Lob
    private String summary;
    /**
     * 活动规则
     */
    @Lob
    private String rules;
    /**
     * 是否删除
     */
    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private Boolean isDeleted;
}
