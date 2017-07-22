package com.niit.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.search.IntegerComparisonTerm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.FriendDao;
import com.niit.dao.UserDao;
import com.niit.model.Connect;
import com.niit.model.Friend;



@RestController
public class FriendController {
	
	@Autowired
	FriendDao friendsDao;
	
	@Autowired
	UserDao usersDao;
	
     @PostMapping("/addfriend")
     public ResponseEntity<List<Connect>> addFriend(@RequestBody Friend friends)
     {
    	  System.out.println("firend id :::::::::::"+friends.getFriendId());
    	   friends.setStatus("pending");
    	   friendsDao.addFriend(friends);
    	   List users= friendsDao.suggestFriends(friends.getUserId());
    	     
      	 return new ResponseEntity<List<Connect>>(users, HttpStatus.OK);
    	   
     }
     
     
     @RequestMapping("/updatefriend/{fid}/{uid}")
     public ResponseEntity<List<Friend>> update(@RequestBody String status,@PathVariable("fid") Integer fid,@PathVariable("uid") Integer uid )
     {
    	 Friend friend=friendsDao.getFriend(fid, uid);
    	 friend.setStatus("approved");
    	 friendsDao.updateFriend(friend);
    	 List<Friend> friends=friendsDao.getFriends(uid);
    	 return new ResponseEntity<List<Friend>>(friends,HttpStatus.ACCEPTED);
     }
     
     
     @PostMapping("/getfriends")
     public ResponseEntity<Collection<Connect>> getFriends(@RequestBody Connect user)
     {
    	 System.out.println(user.getUserid());
    	List<Friend> friends= friendsDao.getFriends(user.getUserid());
    	Set<Connect> friendList=new HashSet();
    	for(Friend f:friends)
    	{
    		Connect fuser=usersDao.getUserId(f.getFriendId());
    		friendList.add(fuser);
    	}
    	for(Friend f:friends)
    	{
    		Connect fuser=usersDao.getUserId(f.getUserId());
    		friendList.add(fuser);
    	}
    	 System.out.println("firends list"+friendList);
    	 
    	return new ResponseEntity<Collection<Connect>>(friendList, HttpStatus.OK) ;
     }
     
     
     @GetMapping("/suggestedFriends/{id}")
     public ResponseEntity<List<Connect>> suggestedFriends(@PathVariable("id") Integer id)
     {
    	 System.out.println("user Id for suggestedFriends:::::"+id);
    	List users= friendsDao.suggestFriends(id);
     
    	 return new ResponseEntity<List<Connect>>(users, HttpStatus.OK);
     }
     
     @PostMapping("/getFriendrequests")
     public ResponseEntity<List<Connect>> getFriendsRequest(@RequestBody Integer userId)
     {
    	 System.out.println("getFriendsrequest");
    	 System.out.println("sizeoflistfriends"+friendsDao.getPendingReqs(userId).size());
    	 List<Connect> users=new ArrayList<>();
    	 int i=0;
    	 for(Friend friend:friendsDao.getPendingReqs(userId))
    	 {
    		 System.out.println(i+"\n");
    		 i++;
    		users.add(usersDao.getUserId(friend.getUserId()));
    	 }
    	 return new ResponseEntity<List<Connect>>(users,HttpStatus.OK);
     }

@PostMapping("/updateStatus/{friendId}/{userId}")
public ResponseEntity<List<Friend>> updateStatus(@RequestBody String status,@PathVariable("friendId") Integer friendId,@PathVariable("userId")Integer userId)
{
	 System.out.println(friendId+"    "+userId);
	  Friend frnd=   friendsDao.getFriend(friendId, userId);
	  System.out.println("thid is friend object  "+frnd);
	  frnd.setStatus("accepted");
	  friendsDao.updateFriend(frnd);
	  
	  
	 return new ResponseEntity<List<Friend>>(friendsDao.getFriends(userId), HttpStatus.OK);
}
}
