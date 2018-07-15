package com.tiaa.news.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tiaa.news.model.ContentType;
import com.tiaa.news.model.News;
import com.tiaa.news.model.Priority;
import com.tiaa.news.repo.ContentTypeRepository;
import com.tiaa.news.repo.PriorityRepository;
import com.tiaa.service.NewsService;

@RestController
@RequestMapping("/news")
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	@Autowired
	private  ContentTypeRepository contentTypeRepo;
	@Autowired
	private  PriorityRepository priorityRepository;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {

		News news = new News();
		news.setHeadLine("HL :" + new Date());
		news.setContent("Content :" + new Date());
		ContentType contentType = contentTypeRepo.findByNameIgnoreCase("sports");
		
		news.setContentType(contentType );
		Priority priority = priorityRepository.findByNameIgnoreCase("Breaking");
		news.setPriority(priority);
		
		newsService.addAndProcessNews(news);

		return "Added :ID " + news.getId();

	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public News getNews(@PathVariable Long id) {
		return  newsService.getNews(id);
	}
	
	@RequestMapping(value = "/top-breaking-news", method = RequestMethod.GET)
	public List<News> getTop100BreakingNews() {
		return  newsService.getTopBreakingNews();
	}
	
	@RequestMapping(value = "/top-news", method = RequestMethod.GET)
	public List<News> getTopNews() {
		return  newsService.getTopNews();
	}
	
	@RequestMapping(value = "/top-news/{type}", method = RequestMethod.GET)
	public List<News> getTopNews(@PathVariable String type) {
		return  newsService.getTopNewsByType(type);
	}
	
	

}
