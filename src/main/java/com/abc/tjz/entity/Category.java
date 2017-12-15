package com.abc.tjz.entity;

import com.abc.tjz.util.db.entity.IdDateEntity;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.*;

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
     * 分类名称
     */
    @Column(length = 50)
    private String name;
    /**
     * 序列
     */
    @ColumnDefault("0")
    private Integer seq;
    /**
     * 父级
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;
    /**
     * 子级
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @OrderBy(value = "seq ASC")
    protected Set<Category> children = new LinkedHashSet<>();

    /**
     * 类别关系
     */
    @ManyToMany
    @JoinTable(name = "t_category_rel",
            joinColumns = {@JoinColumn(name = "rel_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")},
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Set<Category> categories = new LinkedHashSet<>();
}
