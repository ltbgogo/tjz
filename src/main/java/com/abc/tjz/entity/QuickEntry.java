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
 * 商品类别表
 */
@NoArgsConstructor
@Getter 
@Setter
@Table(name = "t_quick_entry")
@Entity
public class QuickEntry extends IdDateEntity {

	/**
	 * 类别
	 */
	@Column(length = 50)
	private String category;

	@Column(length = 50)
	private String name;

	@Column(name = "image_path", length = 500)
	private String imagePath;

	@ColumnDefault("0")
	private Integer seq;
	/**
	 * 跳转链接
	 */
	@Column(length = 1000)
	private String link;
}









