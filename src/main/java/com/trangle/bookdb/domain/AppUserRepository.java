package com.trangle.bookdb.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(exported=false)
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByUserName(String userName);
}
