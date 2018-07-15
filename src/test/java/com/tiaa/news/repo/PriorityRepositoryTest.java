package com.tiaa.news.repo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tiaa.news.model.Priority;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PriorityRepositoryTest {
	@Autowired
	private PriorityRepository priorityRepo;
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testFindAllByOrderByTimeDesc() {
		Priority p1 = new Priority();
		p1.setName("Breaking");
		entityManager.persist(p1);
		Priority priority = priorityRepo.findByNameIgnoreCase("Breaking");
		Assert.assertEquals("Breaking", priority.getName());
	}

}
