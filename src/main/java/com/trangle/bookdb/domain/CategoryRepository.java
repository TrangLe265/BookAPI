package com.trangle.bookdb.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    
    List<Category> findByCategoryName(String categoryName);
}
