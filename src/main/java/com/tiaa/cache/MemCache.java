package com.tiaa.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.stereotype.Component;

import com.tiaa.news.model.News;

@Component
public class MemCache {
	public static final int CACHE_SIZE = 100;
	private PriorityQueue<News> priorityQ =
			new PriorityQueue<>((o1, o2) -> o2.getTime().compareTo(o1.getTime()));

	public void add(News news) {
		if (priorityQ.size() > CACHE_SIZE) {
			priorityQ.remove();
		}
		priorityQ.add(news);
	}

	public List<News> getTopNews() {
		List<News> list = new ArrayList<>();
		while (!priorityQ.isEmpty()) {
			News e = priorityQ.poll();
			list.add(e);
		}
		return list;
	}

}
