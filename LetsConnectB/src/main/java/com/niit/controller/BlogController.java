package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.BlogDao;
import com.niit.dao.UserDao;
import com.niit.model.Connect;
import com.niit.model.MyBlog;

@RestController
public class BlogController {
	@Autowired
	BlogDao blogDao;
	
	@Autowired
	UserDao userDao;
	
	//====Retrieve all blogs=====
	
	@GetMapping(value="/blog/")
    public ResponseEntity<List<MyBlog>> listAllBlogs() 
	{
       List<MyBlog> blogs = blogDao.listBlogs();
       if(blogs.isEmpty())
       {
    	   return new ResponseEntity<List<MyBlog>>(HttpStatus.NO_CONTENT);
       }
		
        return new ResponseEntity<List<MyBlog>>(blogs,HttpStatus.OK);
    }
	
	//======Retrieve all new blogs=======
	
	@GetMapping(value="/blog/new")
    public ResponseEntity<List<MyBlog>> listAllNewBlogs() {
        List<MyBlog> blogs = blogDao.listNewBlogs();
        if(blogs.isEmpty()){
            return new ResponseEntity<List<MyBlog>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<MyBlog>>(blogs, HttpStatus.OK);
    }
	//=========Retrieve single blog=========
	
	@GetMapping(value="/blog/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MyBlog> getBlog(@PathVariable("id") long id) {
        
		MyBlog blog = blogDao.getBlogId(id);
        if (blog == null) {
            
            return new ResponseEntity<MyBlog>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MyBlog>(blog, HttpStatus.OK);
    }
	//========create a blog=============
	
	@PostMapping(value = "/blog/{id}")
    public ResponseEntity<Void> createBlog(@RequestBody MyBlog blog,@PathVariable Integer id) {
		
	 Connect user=userDao.getUserId(id);
	    blog.setUserId(user);
  
        blogDao.addBlog(blog);
  
       
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
	//------------------- Update a User --------------------------------------------------------
	   
	@PutMapping(value = "/blog/{id}")
	   public ResponseEntity<MyBlog> updateBlog(@PathVariable("id") long id, @RequestBody MyBlog blog) {
	     
	         
		MyBlog currentBlog = blogDao.getBlogId(id);
	         
	       if (currentBlog==null) {
	           
	           return new ResponseEntity<MyBlog>(HttpStatus.NOT_FOUND);
	       }
	 
	       currentBlog.setTitle(blog.getTitle());
	       currentBlog.setDescription(blog.getDescription());
	      
	       
	         
	       blogDao.updateBlog(currentBlog);
	       return new ResponseEntity<MyBlog>(currentBlog, HttpStatus.OK);
	   }
	
	//------------------- Delete a blog --------------------------------------------------------
	   @DeleteMapping(value = "/blog/{id}")
	   public ResponseEntity<MyBlog> deleteBlog(@PathVariable("id") long id) {
	       
	 
		   MyBlog blog = blogDao.getBlogId(id);
	       if (blog == null) {
	           
	           return new ResponseEntity<MyBlog>(HttpStatus.NOT_FOUND);
	       }
	 
	       blogDao.deleteBlog(blog);
	       return new ResponseEntity<MyBlog>(HttpStatus.NO_CONTENT);
	   }

	 //-------------------Approve Blog--------------------------------------------------------
	   
	   @PostMapping(value="/approveblog/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	      public ResponseEntity<List<MyBlog>> approveBlog(@PathVariable("id") long id) {
	          
		   MyBlog blog = blogDao.getBlogId(id);
	          
	          if (blog == null) {
	              
	              return new ResponseEntity<List<MyBlog>>(HttpStatus.NOT_FOUND);
	          }
	          blog.setStatus("Approved");
	          blogDao.updateBlog(blog);
	          
	          
	          return new ResponseEntity<List<MyBlog>>(blogDao.listBlogs(), HttpStatus.OK);
	      }
	 //-------------------Reject Blog--------------------------------------------------------
	   
	   @PostMapping(value="/rejectblog/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<List<MyBlog>> rejectBlog(@PathVariable("id") long id) {
	       
		   MyBlog blog = blogDao.getBlogId(id);
	       
	       if (blog == null) {
	           
	           return new ResponseEntity<List<MyBlog>>(HttpStatus.NOT_FOUND);
	       }
	       blog.setStatus("Rejected");
	       blogDao.updateBlog(blog);
	       return new ResponseEntity<List<MyBlog>>(blogDao.listBlogs(), HttpStatus.OK);
	   }
}
