package com.onlineCourse.eduhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EduhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduhubApplication.class, args);
	}

}
