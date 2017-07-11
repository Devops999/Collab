package com.collab.domain;

import javax.persistence.Transient;

//import java.beans.Transient;

import org.springframework.stereotype.Component;

@Component
public class BaseDomain {
	@Transient
	public String errorCode;
	@Transient
	public String errorMessage;
	
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode(){
		return errorCode;
		
	}
	public void setErrorCode(String errorCode){
		this.errorCode=errorCode;
		
	}
	
	

}
