 package com.tiaa.news.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tiaa.news.model.ContentType;

@Repository
public interface ContentTypeRepository extends CrudRepository<ContentType, Long> {
	
	public ContentType findByNameIgnoreCase(String name);

}
