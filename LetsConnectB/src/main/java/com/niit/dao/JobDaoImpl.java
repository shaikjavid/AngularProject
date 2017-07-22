package com.niit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.MyJob;
@Repository("jobDao")
@Transactional
public class JobDaoImpl implements JobDao {
	@Autowired(required = true)
	private SessionFactory sessionFactory;

	
	public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
	}


	public List<MyJob> list(Integer userId) {
	
		return sessionFactory.getCurrentSession().createQuery("from MyJob where jobId not IN (select jobId from JobApplicants where userId=:userId)",MyJob.class)
				.setParameter("userId", userId).getResultList();

	}

	@SuppressWarnings("deprecation")
	public MyJob get(int id) {
		Session session=sessionFactory.getCurrentSession();
		MyJob job=(MyJob)session.createQuery("from MyJob where jobId="+id).getSingleResult();
		return job;

	}

	public void add(MyJob job) {

		System.out.println("i am in add job dao");
		Session session=sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(job);
		tx.commit();

	}

	public void delete(int id) {
		MyJob job = new MyJob();
		job.setJobId(id);
		sessionFactory.getCurrentSession().delete(job);
		
	}


	@Override
	public List<MyJob> appliedJobs(Integer userId) {
		System.out.println("in appliedJobs impl user id is "+userId);
		// TODO Auto-generated method stub
	try
	{
		return sessionFactory.getCurrentSession().createQuery("from MyJob where jobId in (select jobId from JobApplicants where userId=:userId)",MyJob.class)
		.setParameter("userId", userId)
		.getResultList();
	}catch(Exception e)
	{
		System.out.println(e);
		return null;
	}
	}


	

}
	


