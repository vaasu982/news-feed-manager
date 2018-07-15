package com.tiaa.news.repo;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tiaa.news.model.ContentType;
import com.tiaa.news.model.News;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NewsRepositoryTest {
	@Autowired
	private NewsRepository newsRepo;
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testFindAllByOrderByTimeDesc() {
		String headLine = "Head line 1";
		News news = new News();
		news.setHeadLine("Head line 1");
		entityManager.persist(news);
		List<News> list = newsRepo.findAllByOrderByTimeDesc();
		Assert.assertEquals(headLine, list.get(0).getHeadLine());
	}
	
	@Test
	public void testFindByContentTypeNameIgnoreCaseOrderByTimeDesc() {
		String headLine = "Head line 1";
		News news = new News();
		news.setHeadLine("Head line 1");
		ContentType contentType = new ContentType();
		contentType.setName("Finance");
		news.setContentType(contentType );
		entityManager.persist(contentType);
		entityManager.persist(news);
		List<News> list = newsRepo.findByContentTypeNameIgnoreCaseOrderByTimeDesc("Finance");
		
		Assert.assertEquals("Finance", list.get(0).getContentType().getName());
	}

}
