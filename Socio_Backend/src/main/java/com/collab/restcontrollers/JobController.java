package com.collab.restcontrollers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.collab.dao.JobDao;
import com.collab.domain.Job;

public class JobController {
	@Autowired
	JobDao jobDao;
	
	@Autowired
	HttpSession httpSession;
public static final Logger logger = Logger.getLogger("JObController.class");
	
	
	@CrossOrigin(origins="http://localhost:8580")
	
	
	
	//Getting AllJobs
	

	@RequestMapping(value="/getAllJobs/",method=RequestMethod.GET)
	public ResponseEntity<List<Job>> getAllOpenedJobs(){
		logger.debug("starting of method getAllOpenedJobs");
		List<Job> jobs=jobDao.getAllOpenedJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	
	
	//Getting My Applied Jobs
	
	@RequestMapping(value="/getMyAppliedJobs",method=RequestMethod.GET)
	public ResponseEntity<List<Job>> getMyAppliedJobs(){
		logger.debug("Starting of the method getAllOpenedJobs");
		String loggedInUserId=(String) httpSession.getAttribute("loggedInUserId");
		List<Job> jobs=new ArrayList<Job>();
		if(loggedInUserId==null|| loggedInUserId.isEmpty()){
		job.setErrorCode("404");
		job.setErrorMessage("you have to login in to see your applied jobs");
		jobs.add(job);
		
		}
		else{
			jobs=jobDao.getMyAppliedJobs(loggedInUserId);
		}
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
		
	}

	
	
	
	
	//Getting Job Details
	
	@RequestMapping(value="/getJobDetails/{jobId}",method=RequestMethod.GET)
	public ResponseEntity<Job> getJobDetails(@PathVariable("jobId") String JobId){
		Job job=jobDao.getJobDetails(jobId);
		
		if(job==null){
			job=new Job();
			job.setErrorCode("404");
			job.setErrorMessage("job not available for this id:" +jobId);
		}
return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	
	
	
	//Updating JOb
	
	@RequestMapping("/updateJob",meethod=RequestMethod.PUT)
	public ResponseEntity<Job> updateJob(@RequestBody Job job){
		logger.debug("stating of method update");
		
		if(jobDao.updateJob(job)==false){
			job.setErrorCode("404");
			job.setErrorMessage("Not able to update job");
			logger.debug("not able to update job");
		}else{
			job.setErrorCode("200");
			job.setErrorMessage("Successfully posted the updateJob");
		logger.debug("sucessfuly posted updatedJob");
		}
		return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
	
	
	
	//Posting Job
	
	@RequestMapping(value="/postJob",method=RequestMethod.POST)
	public ResponseEntity<Job> postAJob(@RequestBody Job job){
		logger.debug("starting of postJob method");
		job.setStatus('V'); //V->vacant
		if(jobDao.save(job)==false){
			job.setErrorCode("404");
			job.setErrorMessage("not able to post the job");
			logger.debug("not able to post job");
			
		}
		else{
			job.setErrorCode("200");
			job.setErrorMessage("successfully posted job");
			logger.debug("successfully posted job");
		}
		return new ResponseEntity<Job>(job,HttpStatus.OK);
		
	}
	
	
	
	//Applying for job
	
	
	@RequestMapping(value="/applyForJob/{jobId}",method=RequestMethod.POST)
	public ResponseEntity<JobApplication> applyForJob(@PathVariable("jobId") Long jobId)
	logger.debug("starting of method applyForJob");
	String loggedInUserId= null || loggedInUserId.isEmpty()){
		jobApplication.setJobId(jobId);
		jobApplication.setUserId(loggedInUserId);
		jobApplication.setStatus('N');
		jobApplication.setDateApplied(new Date(System.currentTimeMillis()));
		logger.debug("Applied Date:"+JobAppliation.getDateApplied());
		
		if(jobDao.save(jobApplication)){
			jobApplication.setErrorCode("200");
			
			jobApplication.setErrorMessage("you have successfully applied for job");
			logger.debug("Not able to apply for the job");
		}
		else{
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("you already applied for the job"+jobId);
			logger.debug("Not apply for the job");
			
		}
		else{
			jobAppliaction.setErrorCode("404");
			jobApplication.setErrorMessage("you are already apply for the job"+jobId);
			logger.debug(("not able to apply for the job");
			
		}
	}
return new ResponseEntity<JoBapplication>(jobApplication,HttpStatus.OK)
}

//Admin


//Selecting User


@RequestMapping(value="/selectUser/{userId}/{jobId}/(remarks)",method=RequestMethod.GET)
public responseEntity<JobApplication> selectUser(@PathVariable("userId") String userId ,@PathVariable("jobId") Long jobId,@PathVariable("remarks") String remarks)
{
	logger.debug("Starting of method selectUser");
	jobApplication=updateJobApplicationStatus(UserId,jobId,'S',remarks);
	return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
}



//Call for Interview

@RequestMapping(value="/callForInterview/{userId}/{jobId}/(remarks)",method=RequestMethod.GET)
public responseEntity<JobApplication> callForInterview(@PathVariable("userId") String userId ,@PathVariable("jobId") Long jobId,@PathVariable("remarks") String remarks)

{
	logger.debug("Starting of method selectUser");
	jobApplication=updateJobApplicationStatus(UserId,jobId,'C',remarks);
	return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
}

//rejecting job
@RequestMapping(value="/rejectJobApplication/{userId}/{jobId}/{remarks}",method=RequestMethod.GET)
public responseEntity<JobApplication>  rejectJobApplication(@PathVariable("userId") String userId,@PathVariable("jobId") Long jobId,@PathVariable("remarks") String remarks)

{
	logger.debug("Starting of method selectUser");
	jobApplication=updateJobApplicationStatus(UserId,jobId,'R',remarks);
	return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
}



private updateJobApplicationStatus(String userId,Long jobId,char status)
logger.debug("starting of method updateJobApplicationStatus");
if(isUserAppliedForTheJob(userId,jobId)==false){
	jobApplication.setErrorCode("404");
	jobApplication.setErrorMessage("you are not logged In");
	return jobApplication;
}


String loggedInUserRole=httpSession.getAttribute("loggedInUserRole");
loogger.debug("loggedInUserRole"+loggedInUserRole);

if(loggedInUserRole==null|| loggedInUserRole);
{
	jobApplication.setErrorCode("404");
	jobApplication.setErrorMessage("you are not logged in");
	return jobApplication;
	
}
if(loggedInUserRole.equalsIgnoreCase("admin")){
	jobApplication.setErrorCode("404");
	jobApplication.setErrorMesage("you are not admin. you cannot do this operation");
	return jobapplication;
	
}


jobApplication=jobDao.getJobApplication(userId,jobId);
jobApplication.setStatus(status);
jobApplication.setRemarks(remarks);
if(jobDao.updateJob(jobApplication)){
	jobApplication.setErrorCode("200");
	jobApplication.setErrorMessage("Successfully updated the status"+status);
	logger.debug("successfully updated the status as "+ status);
	
}
else{
	    jobApplication.setErrorCode("404");
		jobApplication.setErrorMessage("Not able to update status"+status);
	    logger.debug("not able to update the status"+status);
	}
return jobApplication;
}



private boolean isUserAppliedForTheJob(String userId,Long jobId){
	if(jobDao.getJobApplication(userId,jobId)==null){
		return false;
	}
	return true;
}
}
