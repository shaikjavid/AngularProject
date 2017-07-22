package com.niit.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Connect;
@Transactional
@Repository("userDao")
public class UserDaoImpl implements UserDao
{
	
	@Autowired
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory; 
	}
	@Override
	public void addUser(Connect user) {
		System.out.println("i am in add user dao");
		Session session=sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(user);
		tx.commit();

		
	}
	public Connect getUserByUsername(String username)
	{
		Session session=sessionFactory.getCurrentSession();
		Connect user=(Connect)session.createQuery("from Connect where username='"+username+"'").getSingleResult();
         return user;
	}
	
	@SuppressWarnings({ "uncheked", "unchecked" })
	public List<Connect>listUsers(){
		Session session=sessionFactory.openSession();
		List<Connect> users=session.createQuery("from Connect").getResultList();
		return users;
		}
	@Override
	public boolean isExistingUser(Connect user) {
		Connect u=null;
		try {
		u=getUserByUsername(user.getUsername());
		}catch(NoResultException nre){
		}
		if(u!=null)
		{
		return true;
		}
		else
		return false;
	}

	@Override
	public Connect getEmailid(String email, String password) {
		Session session = sessionFactory.getCurrentSession();
		Connect useremail = (Connect)session.createQuery("from Connect where email ='"+email+"' and password='"+password+"'").getSingleResult();
		System.out.println("emailid="+useremail.getEmail()+"\t password="+useremail.getPassword());
		return useremail;
	}

	@Override
	public Connect getUserId(long userId) {
		Session session=sessionFactory.getCurrentSession();
		Connect user=(Connect)session.createQuery("from Connect where userid="+userId).getSingleResult();
		return user;

		}
	
	
		}
