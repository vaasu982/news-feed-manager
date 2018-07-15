package com.tiaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.tiaa.news.model.ContentType;
import com.tiaa.news.model.Priority;
import com.tiaa.news.repo.ContentTypeRepository;
import com.tiaa.news.repo.PriorityRepository;

@SpringBootApplication
public class NewsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(NewsApplication.class, args);
		initSetup(ctx);
	}
	
	public static void initSetup(ConfigurableApplicationContext ctx){
		// Setting up content type
		ContentTypeRepository contentRepo =ctx.getBean(ContentTypeRepository.class);
		
		ContentType ct1= new ContentType();
		ContentType ct2= new ContentType();
		ContentType ct3= new ContentType();
		ct1.setName("Sports");
		ct2.setName("Finance");
		ct3.setName("General");
		
		contentRepo.save(ct1);
		contentRepo.save(ct2);
		contentRepo.save(ct3);
		
		
		// Setting up Priority
		PriorityRepository priorityRepo =ctx.getBean(PriorityRepository.class);
		Priority p1= new Priority();
		Priority p2= new Priority();
		p1.setName("Breaking");
		p2.setName("General");
		
		priorityRepo.save(p1);
		priorityRepo.save(p2);
		
	}
}
