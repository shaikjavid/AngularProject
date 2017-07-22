package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserDao;
import com.niit.model.Connect;

@RestController
public class UserController {
	@Autowired
	UserDao userDao;

	@GetMapping(value="/user/")
	public ResponseEntity<List<Connect>>  getUsersdata()
	{
	        List<Connect> users = userDao.listUsers();
	  
	        if(users.isEmpty()){
	            return new ResponseEntity<List<Connect>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Connect>>(users, HttpStatus.OK);

	}
	@PostMapping(value = "/usersave/")
    public ResponseEntity<Void> createUser(@RequestBody Connect user) {
        System.out.println("Creating User " + user.getUsername());
  
        if (userDao.isExistingUser(user)) {
            System.out.println("A User with name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
  
        userDao.addUser(user);
  
       
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
     @PostMapping("/login")
      public ResponseEntity<Connect> loginemail(@RequestBody Connect user)
      {
    	
       System.out.println("get the email id :"+user.getEmail());
       System.out.println("password:::::::::::::::::"+user.getPassword());
       Connect usere = userDao.getEmailid(user.getEmail(),user.getPassword());
         if(usere!=null)
         {
        return new ResponseEntity<Connect>(usere,HttpStatus.OK);
         }
       else
        {
        return new ResponseEntity<Connect>(user,HttpStatus.UNAUTHORIZED);
        }
         }

         }
