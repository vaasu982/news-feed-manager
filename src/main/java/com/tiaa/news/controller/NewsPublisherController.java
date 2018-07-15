package com.tiaa.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tiaa.news.model.News;
import com.tiaa.service.NewsService;

@RestController
@RequestMapping("/publish")
public class NewsPublisherController {
	@Autowired
	private NewsService newService;
	
	@RequestMapping(method = RequestMethod.POST, value ="/news")
	public String addNews(@RequestBody News news) {
		try {
		newService.addAndProcessNews(news);
		return "Published ID :"+news.getId();
		} catch(Exception  e) {
			return "Unable to Publish. Invalid format. "+ e.getMessage();
		}
		
	}
	
}
