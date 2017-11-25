package com.abc.tjz.repository;

import com.abc.tjz.entity.Category;
import com.abc.tjz.util.db.repository.IdRepository;

import java.util.List;

public interface CategoryRepository extends IdRepository<Category> {

    List<Category> findByFirstOrderBySeq(String first);
}
