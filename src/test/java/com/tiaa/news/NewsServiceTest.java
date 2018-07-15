package com.tiaa.news;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.tiaa.cache.MemCache;
import com.tiaa.news.model.ContentType;
import com.tiaa.news.model.News;
import com.tiaa.news.model.Priority;
import com.tiaa.news.repo.ContentTypeRepository;
import com.tiaa.news.repo.NewsRepository;
import com.tiaa.news.repo.PriorityRepository;
import com.tiaa.service.NewsService;

public class NewsServiceTest {

	@InjectMocks
	private NewsService newsService;
	@Mock
	private NewsRepository newsRepo;
	@Mock
	private MemCache memCache;
	@Mock
	private ContentTypeRepository contentTypeRepo;
	@Mock
	private PriorityRepository priorityRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddAndProcessNews() {
		News news = new News();
		news.setHeadLine("Head line 1");
		ContentType contentType = new ContentType();
		contentType.setName("Breaking");
		news.setContentType(contentType);
		when(contentTypeRepo.findByNameIgnoreCase(anyString())).thenReturn(contentType);
		Priority priority = new Priority();
		priority.setName("Breaking");
		news.setPriority(priority);
		when(priorityRepository.findByNameIgnoreCase(anyString())).thenReturn(priority);
		when(newsRepo.save(Mockito.any(News.class))).thenReturn(news);
		News news1 = newsService.addAndProcessNews(news);
		Assert.assertNotNull(news1);

	}

	@Test
	public void testGetNews() {
		News news = new News();
		news.setId(1L);
		news.setHeadLine("Headline 1");
		when(newsRepo.findOne(1L)).thenReturn(news);
		News returnNews = newsService.getNews(1L);
		Assert.assertEquals("Headline 1", returnNews.getHeadLine());

	}

	@Test
	public void testGetTopBreakingNews() {
		List<News> list = new ArrayList<>();
		list.add(new News());
		when(memCache.getTopNews()).thenReturn(list);
		List<News> returnList = newsService.getTopBreakingNews();
		Assert.assertTrue(returnList.size()>0);
	}

	@Test
	public void testGetTopNews() {
		List<News> list = new ArrayList<>();
		list.add(new News());
		when(newsRepo.findAllByOrderByTimeDesc()).thenReturn(list);
		List<News> returnList = newsService.getTopNews();
		Assert.assertTrue(returnList.size()>0);
	}

	@Test
	public void testGetTopNewsByType() {
		List<News> list = new ArrayList<>();
		list.add(new News());
		when(newsRepo.findByContentTypeNameIgnoreCaseOrderByTimeDesc("breaking")).thenReturn(list);
		List<News> returnList = newsService.getTopNewsByType("breaking");
		Assert.assertTrue(returnList.size()>0);
	
	}

}
