package com.niit.testcase;

import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.config.ApplicationContextConfig;
import com.niit.dao.BlogDao;
import com.niit.dao.JobDao;
import com.niit.dao.UserDao;
import com.niit.model.Connect;
import com.niit.model.MyBlog;
import com.niit.model.MyJob;

public class AppTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) 
	{
		
		System.out.println("main started");
		@SuppressWarnings("unused")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
		UserDao userdao=(UserDao)context.getBean("userDao");
		Connect user=new Connect();
		/*user.setUsername("javid");
		user.setContactno("9177670825");
		user.setOnlinemail("javid1029@gmail.com");
		user.setPassword("password");
		user.setIsactive(true);
		user.setRole("user");
		userdao.addUser(user);
		
		System.out.println("record entered successfully");
	
*/
	/*	BlogDao blogdao=(BlogDao)context.getBean("blogDao");
		MyBlog blog= new MyBlog();
		blog.setDescription("collab");
		blog.setTitle("connecteach");
		blog.setUser(userdao.getUserId(2));
		blogdao.addBlog(blog);
		
		System.out.println("Successfully entered the record");
			
	}

	
*/
		JobDao jobDao=(JobDao)context.getBean("jobDao");
		MyJob job=new MyJob();
		job.setCompanyname("Apple");
		job.setDescription("Develop frontend as well as backend");
		job.setQualification("B.tech");
		job.setTitle("Developer");
		job.setDated(new Date());
		job.setStatus("available");
		jobDao.add(job);
		System.out.println("Successfully entered the record");
		
	}
		}
