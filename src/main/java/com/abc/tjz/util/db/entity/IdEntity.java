package com.abc.tjz.util.db.entity;

import java.io.Serializable;
import java.security.SecureRandom;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

/**
 * 抽象字段：主键
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", length = 36)
	private String id;

	@PrePersist
	protected void prePersist() {
		this.newIdIfBlank();
	}
	
	public void newIdIfBlank() {
		if (StringUtils.isBlank(this.getId())) {
			this.setId(StringUtils.rightPad(Math.abs(new SecureRandom().nextLong()) + "", 12, '0').substring(0, 12));
		}
	}
}
