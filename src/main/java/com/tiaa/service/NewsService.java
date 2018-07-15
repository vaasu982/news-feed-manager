package com.tiaa.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiaa.cache.MemCache;
import com.tiaa.news.model.ContentType;
import com.tiaa.news.model.News;
import com.tiaa.news.model.Priority;
import com.tiaa.news.repo.ContentTypeRepository;
import com.tiaa.news.repo.NewsRepository;
import com.tiaa.news.repo.PriorityRepository;
import com.tiaa.util.NewsConstant;

@Service
public class NewsService {
	@Autowired
	private NewsRepository newsRepo;
	@Autowired
	private MemCache memCache;
	@Autowired
	private ContentTypeRepository contentTypeRepo;
	@Autowired
	private PriorityRepository priorityRepository;

	public News addAndProcessNews(News news) {
		if (news != null && news.getTime() == null) {
			news.setTime(new Date());
		}
		if (news != null && news.getContentType() != null) {
			ContentType contentType = contentTypeRepo.findByNameIgnoreCase(news.getContentType().getName());
			news.setContentType(contentType);
		} else {
			throw new IllegalArgumentException();
		}
		if (news.getPriority() != null) {
			Priority priority = priorityRepository.findByNameIgnoreCase(news.getPriority().getName());
			news.setPriority(priority);
		} else {
			throw new IllegalArgumentException();
		}

		if (NewsConstant.BREAKING_NEWS.equalsIgnoreCase(news.getPriority().getName())) {
			memCache.add(news);
		}
		news = newsRepo.save(news);
		return news;
	}

	public News getNews(Long id) {
		return newsRepo.findOne(id);
	}

	public Set<News> getTopBreakingNews() {
		return memCache.getTopNews();
	}

	public List<News> getTopNews() {
		return newsRepo.findAllByOrderByTimeDesc();
	}

	public List<News> getTopNewsByType(String name) {
		return newsRepo.findByContentTypeNameIgnoreCaseOrderByTimeDesc(name);
	}

}
