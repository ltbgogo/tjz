package com.abc.tjz.entity;

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
@Table(name = "t_quan_tb")
@Entity
public class QuanTb extends IdEntity {

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
    @Column(name = "old_price")
    private Integer oldPrice;
    @Column(name = "quan_price")
    private Integer quanPrice;
    @Column(name = "buy_price")
    private Integer buyPrice;
    @Column(length = 1000)
    private String link;
}
