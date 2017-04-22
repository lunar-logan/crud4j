package com.crud4j.repository;

import com.crud4j.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Dell on 22-04-2017.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
