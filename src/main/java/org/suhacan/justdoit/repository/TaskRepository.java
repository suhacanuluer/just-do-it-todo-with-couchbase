package org.suhacan.justdoit.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;
import org.suhacan.justdoit.entity.Task;

import java.util.List;

@Repository
public interface TaskRepository extends CouchbaseRepository<Task, String> {
    List<Task> findAllByCategoryId(String categoryId);
}
