package com.learn.reactive.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Blocking non reactive mongo db repository
 */


@Repository
public interface UserRepository extends MongoRepository<User, String> {
    //
}