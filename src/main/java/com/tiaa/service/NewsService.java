 package com.tiaa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiaa.cache.MemCache;
import com.tiaa.news.model.News;
import com.tiaa.news.repo.NewsRepository;
import com.tiaa.util.NewsConstant;

@Service
public class NewsService {
	@Autowired
	private NewsRepository newsRepo;
	
	@Autowired
	private MemCache memCache;
 
	
	public News addAndProcessNews(News news) {
		if(news != null && news.getTime() == null) {
			news.setTime(new Date());
		}
		if( NewsConstant.BREAKING_NEWS.equalsIgnoreCase(news.getPriority().getName())) {
			memCache.add(news);
		}
		news =newsRepo.save(news);
		System.out.println("Generated ID:"+ news.getId());
		return news;
	}


	public News getNews(Long id) {
		return newsRepo.findOne(id);
	}
 
	public List<News> getTopNews() {
		return  memCache.getTopNews();
	}

}
