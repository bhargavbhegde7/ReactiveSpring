package com.learn.reactive.service;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Reactive repository for mongo db
 */
@Repository
//public interface UserRepositoryReactive extends ReactiveMongoRepository<User, String> {}
public interface UserRepositoryReactive extends ReactiveCrudRepository<User, String> {
}
