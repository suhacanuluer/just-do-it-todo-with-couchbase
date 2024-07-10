package org.suhacan.justdoit.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;
import org.suhacan.justdoit.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CouchbaseRepository<User, String> {
    Boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
