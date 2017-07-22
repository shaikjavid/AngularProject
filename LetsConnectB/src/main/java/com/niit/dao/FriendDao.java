package com.niit.dao;

import java.util.List;

import com.niit.model.Connect;
import com.niit.model.Friend;

public interface FriendDao {

	boolean addFriend(Friend friends);
	boolean updateFriend(Friend friends);
	List<Friend> getFriends(Integer id); 
	List<Connect> suggestFriends(Integer id);
	public List<Friend> getPendingReqs(Integer userId);
	public Friend getFriend(Integer friendId,Integer userId);
	
}
