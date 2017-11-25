package com.abc.tjz.entity;

import com.abc.tjz.util.db.entity.IdDateEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author LiuTongbin
 * @date 2017/11/25 0025 16:35
 */
@NoArgsConstructor
@Getter
@Setter
@Table(name = "t_category")
@Entity
public class Category extends IdDateEntity {

    /**
     * 一级分类
     */
    @Column(length = 50)
    private String first;
    /**
     * 二级分类
     */
    @Column(length = 50)
    private String second;
    /**
     * 分类名称
     */
    @Column(length = 50)
    private String name;
    /**
     * 序列
     */
    @ColumnDefault("0")
    private Integer seq;
}
