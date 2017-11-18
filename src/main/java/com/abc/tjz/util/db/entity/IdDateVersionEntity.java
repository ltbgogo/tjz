package com.abc.tjz.util.db.entity;

import java.security.SecureRandom;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class IdDateVersionEntity extends IdDateEntity {

	@Version
	private Long version;
}





