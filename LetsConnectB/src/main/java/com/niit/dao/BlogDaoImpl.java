package com.niit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.MyBlog;

@Repository("blogDao")
@Transactional
public class BlogDaoImpl implements BlogDao 
{
	
	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
	}

	
	public void addBlog(MyBlog blog) {
		
		blog.setStatus("New");
		Session session=sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(blog);
		tx.commit();
	
		
	}

	public void updateBlog(MyBlog blog) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(blog);
		
		
	}

	public void deleteBlog(MyBlog blog) {
		
		Session session=sessionFactory.getCurrentSession();
		session.delete(blog);
		
	}

	public MyBlog getBlogId(long blogId) {
	
		Session session=sessionFactory.getCurrentSession();
		MyBlog blog=(MyBlog)session.createQuery("from MyBlog where blogId="+blogId).getSingleResult();
		
		return blog;


	}

	@SuppressWarnings("unchecked")
	public List<MyBlog> listBlogs() {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		   List<MyBlog> blogs=session.createQuery("from MyBlog", MyBlog.class).getResultList();
		tx.commit();
		
		return blogs;

	}

	@SuppressWarnings("unchecked")
	public List<MyBlog> listNewBlogs() {
		
		
		Session session=sessionFactory.getCurrentSession();
		List<MyBlog> blogs=session.createQuery("from MyBlog where status='New'").getResultList();
		
		return blogs;
	
	}

}
