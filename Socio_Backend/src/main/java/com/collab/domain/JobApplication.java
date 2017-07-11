package com.collab.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class JobApplication extends BaseDomain {
@Id
private Long id;
//@Column(name="user_id")
private String userID;

//@Column(name="job_id")
//private Long jobID;
private String jobID;

@Column(name="date_applied")
private Date dateApplied;
private String remarks;
private char status;//S->Select,R->Reject,C->Call For Interview

//generaate getters and setters
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getUserID() {
	return userID;
}
public void setUserID(String userID) {
	this.userID = userID;
}
/*public Long getJobID() {
	return jobID;
}
*/
public Date getDateApplied() {
	return dateApplied;
}


//jobId
public String getJobID() {
	return jobID;
}
public void setJobID(String jobID) {
	this.jobID = jobID;
}




public void setDateApplied(Date dateApplied) {
	if(dateApplied==null){
		dateApplied=new Date(System.currentTimeMillis());
	}
	this.dateApplied = dateApplied;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public char getStatus() {
	return status;
}
public void setStatus(char status) {
	this.status = status;
}







}
