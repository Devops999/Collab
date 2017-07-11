package com.collab.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class Job extends BaseDomain{
	@Id
	private long id;
	private String title;
	private String description;
	@Column(name="date_time")
	private Date dateTime;
	private String qualification;
	private char status;//V-Vacant; C-code
	//generting getters  and setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		if(dateTime==null){
			dateTime=new Date(System.currentTimeMillis());
		}
		this.dateTime = dateTime;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		if(status==null)
		{
			status=new Character('V');
		}
		this.status = status;
	}
	
	
	
	

}
