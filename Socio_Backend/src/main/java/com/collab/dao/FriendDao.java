package com.collab.dao;

import java.util.List;

import com.collab.domain.Friend;
import com.collab.domain.BaseDomain;

public interface FriendDao {

		public List<Friend> getMyFriends(String FriendId);
		public Friend get(String userId,String friendId);
		//public Friend get(String friendId);
		
		//if you want to get all details of your friend
		//you can get(userId) of UserDao Interface
		public boolean save(Friend friend);
		public boolean update(Friend friend);
		public void  delete(String userId,String friendId);
		
		//sselect * from friend where friendId=? and status="N
	public List<Friend> getNewFriendRequests(String userId);

	public void setOnline(String friendId);
	public void setOffline(String friendId);
//	public List<Friend> getRequestSendByMe(String loggedInUserId);
	
	public List<Friend>getRequestsSendByMe(String userId);
	//public List<Friend> getRequestSendByMe(String loggedInUserId);


	}



