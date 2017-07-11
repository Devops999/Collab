package com.collab.restcontrollers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collab.dao.FriendDao;
import com.collab.dao.UserDao;
import com.collab.domain.User;
import com.collab.domain.Error;

@RestController
public class UserRestCon {
	@Autowired
	HttpSession session;
	
	@Autowired
	FriendDao friendDao;
	@Autowired
	UserDao userDao;
	@Autowired 
	public static final Logger logger=Logger.getLogger("UserRestController.class");
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user){
		logger.debug("calling metod authenticate");
		user = userDao.authentication(user.getUserId(), user.getPassword());
		if(user==null){
			user=new User(); //do we need to create user?
			user.setErrorCode("404");
			user.setErrorMessage("Invalid credentials please enter valid credentials");
			logger.debug("invalid credentials");
			
		}
		else{
			user.setErrorCode("200");
			user.setErrorMessage("you have already logged in");
			user.setIsOnline("Y");
			logger.debug("valid Credentials");
			
			//session.
			
			session.setAttribute("loggedInUserId",user.getUserId());
			session.setAttribute("loggedInUserRole",user.getRole());
			
			logger.debug("you are login with role:"+session.getAttribute("loggedInUser"));
		friendDao.setOnline(user.getUserId());
		userDao.setOnline(user.getUserId());
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ResponseEntity<User> logout(HttpSession session){
		logger.debug("calling method logout");
		return User;
		
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers(){
		logger.debug("calling method listALlUser");
		List<User> users=userDao.getAllUser();{
			
			if(users.isEmpty()){
				user.setErrorCode("404");
				user.setErrorMessgae("NO users are available");
				users.add(user);
			}
			return new ResponseEntity<List<User>>(users,HttpStatus.OK);
			
			}
		}
	
	
	
	
	@RequestMapping(value="/listAllUsersNotFriends",method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsersNotFriends(){
		logger.debug("calling method listAllUsers");
		String loggedInUserId =(String) session.getAttribute("loggedInUserId");
		
		logger.debug("loggedd in user id is"+loggedInUserId);
		return User;
	}

	/*@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody User user) {

		System.out.println(user.toString());
		userDao.save(user);

		return new ResponseEntity<String>("successfuly inserted", HttpStatus.OK);

	}*/

	/*@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUser() {
		System.out.println("Hit***");
		List<User> listusers = userDao.getAllUser();
		return new ResponseEntity<List<User>>(listusers, HttpStatus.OK);

	}*/
	@RequestMapping(value="/registration",method=RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody User user)
	{
		try{
		user.setEnabled(true); 
		List<User> userList=userDao.getAllUser();
		for(User u:userList)
		{
			if(u.getFirstName().equals(user.getFirstName()))
			{
				Error error=new Error(2,"Username already exists");
				return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR); 
			}
		}
		//user.setIsOnline(false);
		user.setIsOnline("N");
		userDao.registration(user); 
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			Error error=new Error(1,"Cannot register User");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	}


