package com.abc.tjz.util.db.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 抽象字段：主键、创建日期、更新日期
 */
@Getter
@Setter
@MappedSuperclass
public class IdDateEntity extends IdEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录创建时间
	 */
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	/**
	 * 记录更新时间
	 */
	@Column(name = "update_date")
	private LocalDateTime updateDate;
	
	@PrePersist
	protected void prePersist() {
		super.prePersist();
		this.setCreateDate(LocalDateTime.now());
	}
	
	@PreUpdate
	protected void preUpdate() {
		this.setUpdateDate(LocalDateTime.now());
	}
}
