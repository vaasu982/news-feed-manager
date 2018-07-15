package com.tiaa.news;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.tiaa.cache.MemCache;
import com.tiaa.news.model.News;

public class MemCacheTest {
	
	 
	private MemCache memCache = new MemCache();

	@Test
	public void addTest() throws InterruptedException {
		for(int i =0 ; i <1500; i++) {
			News news = new News();
			Date d1 = new Date();
			System.out.println("Added :"+d1);
			news.setTime(d1);
			Thread.sleep(1001);
			memCache.add(news);
		}
		System.out.println(memCache.getTopNews());
		Assert.assertTrue(memCache.CACHE_SIZE == memCache.getTopNews().size());
	}
	
	 
}
