package com.abc.tjz.util.db.repository;

import com.abc.tjz.util.db.entity.IdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IdRepository<D extends IdEntity> extends GenericRepository<D, String>, JpaSpecificationExecutor<D> {

}




