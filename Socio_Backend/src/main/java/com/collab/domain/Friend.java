package com.collab.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.collab.domain.BaseDomain;

import org.springframework.stereotype.Component;

@Entity

@Component

public class Friend {
	@Id
	private int id;
	//@Column(name="user_id")
	private String userId;
	//@Column(name="friend_id")
	private String friendId;
	
	//New ,accepted,rejected
	private String status;
	//@Column(name="Last_Seen_Time")
	private Date lastSeenTime;
	
	
	
	
	private String Online;
		// TODO Auto-generated method stub
	
	public String getOnline() {
		return Online;
	}
	public void setOnline(String Online) {
		this.Online = Online;
	}
	//generating getters and Setters
	
	
	public int getId() {
		return id;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getLastSeenTime() {
		return lastSeenTime;
	}
	public void setLastSeenTime(Date lastSeenTime) {
		this.lastSeenTime = lastSeenTime;
	}
	
	
		
	
	
	
	
	
	
	

}
