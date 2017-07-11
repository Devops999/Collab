package com.collab.restcontrollers;

import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

//import org.hibernate.annotations.common.util.impl.Log_.logger;

import org.apache.log4j.Logger;



//import org.hibernate.annotations.common.util.impl.Log_.logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collab.dao.FriendDao;
import com.collab.dao.UserDao;
//import com.collab.domain.Error;
import com.collab.domain.Friend;


import com.collab.domain.BaseDomain;


@RestController

public class FriendRestController  {
	@Autowired 
	UserDao userDao;
	@Autowired
	FriendDao friendDao;
	@Autowired
	Friend friend;
	@Autowired
	BaseDomain baseDomain;
	@Autowired
	HttpSession httpSession;
	final static Logger logger=Logger.getLogger(FriendRestController.class);
	
	
	@RequestMapping(value="/myFriends",method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriends(){
		logger.debug("calling method getMyFriend");
		String loggedInUserId= (String) httpSession.getAttribute("loggedInUserId");
		List<Friend> myFriends=new ArrayList<Friend> () ;
		if(loggedInUserId==null)
		{
			baseDomain.setErrorCode("404");
			baseDomain.setErrorMessage("please login to your Friend");
			myFriends.add(friend);
			return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);
			
		}
		logger.debug("getting friends of :" +loggedInUserId);
		myFriends=friendDao.getMyFriends(loggedInUserId);
		
		if(myFriends.isEmpty()){
			logger.debug("friend doest not exit for user:"+loggedInUserId);
			baseDomain.setErrorCode("404");
			baseDomain.setErrorMessage("you doesnot have any friend");
			myFriends.add(friend);
			
		}
		logger.debug("send the friendlit");
		return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);
		
	}



	
//	@RequestMapping(value="/myFriends",method=RequestMethod.GET)
	@RequestMapping(value="/addFriend/{friendId}",method=RequestMethod.GET)
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable ("friendId") String friendId){
     logger.debug(">>>caling method sendFriendRequest");
	String loggedInUserId=(String)httpSession.getAttribute("loggedInUserId");
	friend.setUserId(loggedInUserId);
	friend.setFriendId(friendId);
	friend.setStatus("N");
	friend.setOnline("N");//N->new,R->reject,A->Accept
	
	
	
	//is user already send the request previous
	//check whether friend exist in user table orr not
	
	if(isUserExist(friendId) == false)
			{
		baseDomain.setErrorCode("404");
		baseDomain.setErrorMessage("no user exist with id:" +friendId);
			}	
	else
		
		
	if(friendDao.get(loggedInUserId,friendId)!=null);
	{
			baseDomain.setErrorCode("404");
			baseDomain.setErrorMessage("you already send the friend request to "+friendId);
		}
	/*	else {
			friendDao.save(friend);
			baseDomain.setErrorCode("200");
			baseDomain.setErrorMessage("friendRequsetSuccesfull..."+friendId);
}*/
	return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	
	
}
	

private boolean isUserExist(String userID){
	if(userDao.getUserById(userID)==null)
		return false;
	else
return true;
	}

private boolean isRequestAlreadysent(String friendId)
{
	String loggedInUserId=(String) httpSession.getAttribute("loggedInUserId");
if(friendDao.get(loggedInUserId,friendId)==null)
	return false;
else 
	return true;
}


@RequestMapping(value="/unfriend/{friendId}",method=RequestMethod.PUT)
public ResponseEntity<Friend> unfriend(@PathVariable("friendId")String friendId){
logger.debug(">>>calling method unfriend");
updateRequest(friendId,"U");
return new ResponseEntity<Friend>(friend,HttpStatus.OK);
}


@RequestMapping(value="/rejectFriend/{friendId}",method=RequestMethod.PUT)
public ResponseEntity<Friend> rejectFriendFriendRequest(@PathVariable("friendId")String friendId){
logger.debug(">>>calling method rejectFriendFriendRequset");
updateRequest(friendId,"R");
return new ResponseEntity<Friend>(friend,HttpStatus.OK);
}

@RequestMapping(value="/acceptedFriend/{friendId)",method=RequestMethod.PUT)
public ResponseEntity<Friend> acceptFriendRequest(@PathVariable("friendId")String friendId){
logger.debug(">>calling the method acceptFriendRequest");
updateRequest(friendId,"A");
return new ResponseEntity<Friend>(friend,HttpStatus.OK);
}


private Friend updateRequest(String friendId,String status){
	logger.debug("starting of the method updateRequest");
	String loggedInUserId=(String) httpSession.getAttribute("loggedInUserId");
	logger.debug("loggedInUserId:"+loggedInUserId);
	if(isRequestAlreadysent(friendId)==false)
	{
		baseDomain.setErrorCode("404");
		baseDomain.setErrorMessage("the request doest exist.so you can not update to"+status);
	}
	if(status.equals("A")||status.equals("R"))
		friend=friendDao.get(friendId,loggedInUserId);
	else
		friend=friendDao.get(loggedInUserId,friendId);
	friend.setStatus(status);//N-New,R-Rejcted,a-accepted
	friendDao.update(friend);
	baseDomain.setErrorCode("200");
	baseDomain.setErrorMessage("Requset from"+ friend.getUserId()+"To"+friend.getFriendId()+"");
	logger.debug("ending of the method update request");
	return friend;
}

@RequestMapping(value="/getMyfriendRequests/",method=RequestMethod.GET)
public ResponseEntity<List<Friend>> getMyFriendRequests(){
	logger.debug(">>calling the method get myFriendRequest");
	String loggedInUserId=(String) httpSession.getAttribute("loggdInUserId");
	List<Friend>myFriendRequest=friendDao.getNewFriendRequests(loggedInUserId);
	return new ResponseEntity<List<Friend>>(myFriendRequest,HttpStatus.OK);
}


@RequestMapping(value="/getRequestSendByMe",method=RequestMethod.GET)
public ResponseEntity<List<Friend>> getRequestSendbyMe(){

	logger.debug(">>>calling method getRequestSendByMe");
	String loggedInUserId=(String) httpSession.getAttribute("loggedInUserId");
	List<Friend> requestSendByMe=friendDao.getRequestsSendByMe(loggedInUserId);
	
	logger.debug("");
	return new ResponseEntity<List<Friend>>(requestSendByMe,HttpStatus.OK);
	
		
	}
}