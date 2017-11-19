package com.abc.tjz.repository;

import com.abc.tjz.entity.QuickEntry;
import com.abc.tjz.util.db.repository.IdRepository;

import java.util.List;

public interface QuickEntryRepository extends IdRepository<QuickEntry> {

    List<QuickEntry> findByCategoryOrderBySeq(String category);
}
