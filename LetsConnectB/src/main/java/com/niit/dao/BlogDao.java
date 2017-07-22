package com.niit.dao;

import java.util.List;

import com.niit.model.MyBlog;

public interface BlogDao {
	public void addBlog(MyBlog blog);
	public void updateBlog(MyBlog blog);
	public void deleteBlog(MyBlog blog);
	public MyBlog getBlogId (long blogId);
	public List<MyBlog> listBlogs();
	public List<MyBlog> listNewBlogs();

}
