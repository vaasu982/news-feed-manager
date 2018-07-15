package com.tiaa.cache;

import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.tomcat.jni.Lock;
import org.springframework.stereotype.Component;

import com.tiaa.news.model.News;

/**
 * @author Markandey
 * 
 * It is LRU cache implemented based on News publishing time.
 *
 */
@Component
public class MemCache {
	public static final int CACHE_SIZE = 100;
	// Initially data will be added in blocking queue
	private BlockingQueue<News> bq = new LinkedBlockingQueue<>();
	private Set<News> top100List = new TreeSet<News>((o1, o2) -> o2.getTime().compareTo(o1.getTime()));
	// Only one thread can add data at time.
	private Object lock = new Lock();
	private PriorityQueue<News> priorityQ = new PriorityQueue<>((o1, o2) -> o1.getTime().compareTo(o2.getTime()));

	public void add(News news) {
		bq.add(news);
		while (!bq.isEmpty()) {
			synchronized (lock) {
				if (priorityQ.size() >= CACHE_SIZE) {
					News obj = priorityQ.peek();
					if (news.getTime().after(obj.getTime())) {
						News removeObj = priorityQ.remove();
						top100List.remove(removeObj);
					}
				}
				try {
					News addObj = bq.take();
					priorityQ.add(addObj);
					top100List.add(addObj);
				} catch (InterruptedException e) {
					System.err.println("Some error occured while adding in cache");
				}
			}
		}
	}

	public Set<News> getTopNews() {
		return top100List;
	}

}
