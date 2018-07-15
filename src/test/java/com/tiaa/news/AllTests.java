package com.tiaa.news;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tiaa.news.repo.NewsRepositoryTest;
import com.tiaa.news.repo.PriorityRepositoryTest;

@RunWith(Suite.class)
@SuiteClasses({ NewsServiceTest.class, NewsRepositoryTest.class,PriorityRepositoryTest.class, MemCacheTest.class })
public class AllTests {

}
