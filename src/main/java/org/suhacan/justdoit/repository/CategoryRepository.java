package org.suhacan.justdoit.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;
import org.suhacan.justdoit.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends CouchbaseRepository<Category, String> {
    List<Category> findAllByUserId(String userId);
}
