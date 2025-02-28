package com.trangle.bookdb.domain;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    //For more complex queries, use the JpaSpecificationExecutor interface along with the Specification class.
    //eg in real life, if customer want to search for an item with multiple conditions, eg: size, brand, color, etc.
    Book findByTitle(String title); 
    Book findByAuthor(String author);

    //with larger data set, use PagingAndSortingRepository interface        
}