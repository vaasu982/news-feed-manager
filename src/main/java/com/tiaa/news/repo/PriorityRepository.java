 package com.tiaa.news.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tiaa.news.model.Priority;

@Repository
public interface PriorityRepository extends CrudRepository<Priority, Long> {
	public Priority findByNameIgnoreCase(String name);
}
