package com.tiaa.news;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import com.tiaa.cache.MemCache;
import com.tiaa.news.model.News;

public class MemCacheTest {
	
	 
	private MemCache memCache = new MemCache();

	@Test
	public void addTest() throws InterruptedException {
		for(int i =0 ; i <150; i++) {
			News news = new News();
			news.setTime(new Date());
			//Thread.sleep(1);
			memCache.add(news);
		}
		Assert.assertTrue(100 == memCache.getTopNews().size()  );
	}
	
	 
}
