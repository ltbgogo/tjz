package com.abc.tjz.entity;

import com.abc.tjz.util.db.entity.IdDateEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 商品类别表
 */
@NoArgsConstructor
@Getter 
@Setter
@Table(name = "t_goods_category")
@Entity
public class GoodsCategory extends IdDateEntity {

	private static final long serialVersionUID = 1L;

}









