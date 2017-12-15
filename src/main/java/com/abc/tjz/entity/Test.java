package com.abc.tjz.entity;

import com.abc.tjz.util.db.entity.IdDateEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author LiuTongbin
 * @date 2017/12/2 0002 12:31
 */
@NoArgsConstructor
@Getter
@Setter
@Table(name = "t_test")
@Entity
public class Test extends IdDateEntity {

    private String name;
}
