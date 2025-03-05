package com.trangle.bookdb.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    //For more complex queries, use the JpaSpecificationExecutor interface along with the Specification class.
    //eg in real life, if customer want to search for an item with multiple conditions, eg: size, brand, color, etc.

    //All manually defined methods in Repo is not considered as Spring Data Rest, therefore even if spring.data.rest.base-path=/api is defined in application.properties, /api path does not applied for manually defined method --> this can be overriden if @RequestMapping("/api") is defined again in the Controller, above all leveles of endpoints
    List<Book> findByTitle(String title);

    //with larger data set, use PagingAndSortingRepository interface        
}