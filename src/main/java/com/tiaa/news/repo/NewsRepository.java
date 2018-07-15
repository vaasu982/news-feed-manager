 package com.tiaa.news.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tiaa.news.model.News;

@Repository
public interface NewsRepository extends CrudRepository<News, Long> {

}
