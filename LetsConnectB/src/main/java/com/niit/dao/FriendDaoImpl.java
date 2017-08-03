package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Connect;
import com.niit.model.Friend;

@Repository("friendsDao")
@Transactional
public class FriendDaoImpl implements FriendDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean addFriend(Friend friendShip) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(friendShip);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean updateFriend(Friend friendShip) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(friendShip);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public List<Friend> getFriends(Integer id) {
		System.out.println("this is friends dao impl:userid is"+id);
		// TODO Auto-generated method stub
		try {
		return	sessionFactory.getCurrentSession().createQuery("from Friend where userId=:uid or friendId=:id",Friend.class)
				.setParameter("uid", id)
				.setParameter("id", id)
				.getResultList();
		
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<Connect> suggestFriends(Integer id) {
		// TODO Auto-generated method stub
		try {
		return	(List<Connect>)sessionFactory.getCurrentSession().createSQLQuery("select * from Connect_User where userid not in ((select friendid from Friend where userid=? or friendid=?) union (select userid from Friend where userid=? or friendid=?))")
				.addEntity(Connect.class)
			.setInteger(0, id)
			.setInteger(1, id)
			.setInteger(2, id)
			.setInteger(3, id).getResultList();
			
			
			 
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	
	public List<Friend> getPendingReqs(Integer userId) {
		try{
			System.out.println("friendsDaouserid:::::::"+userId);

		return sessionFactory.getCurrentSession().createQuery("from Friend where friendId=:id and status=:status",Friend.class).setParameter("id", userId).
	setParameter("status","pending" ).getResultList();
	
	
		
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	
	
		public Friend getFriend(Integer friendId,Integer userId)
		{
			try {
				return sessionFactory.getCurrentSession().createQuery("from Friend where friendId=:frndId and userId=:userId",Friend.class).setParameter("frndId", friendId)
				.setParameter("userId", userId).getSingleResult();
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		}

	}
