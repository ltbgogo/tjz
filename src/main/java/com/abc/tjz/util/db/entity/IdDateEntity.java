package com.abc.tjz.util.db.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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
	@Column(name = "create_datetime")
	@ColumnDefault("current_timestamp")
	private LocalDateTime createDateTime;
	
	/**
	 * 记录更新时间
	 */
	@Column(name = "update_datetime")
	@ColumnDefault("current_timestamp")
	private LocalDateTime updateDateTime;
	
	@PrePersist
	protected void prePersist() {
		super.prePersist();
		this.setCreateDateTime(LocalDateTime.now());
		this.setUpdateDateTime(this.getCreateDateTime());
	}
	
	@PreUpdate
	protected void preUpdate() {
		this.setUpdateDateTime(LocalDateTime.now());
	}
}
