package com.collab.restcontrollers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.collab.dao.JobDao;
import com.collab.dao.TJobsDao;
import com.collab.domain.Job;
import com.collab.domain.JobApplication;

@RestController("/job")
public class TJobsController {
	@Autowired 
	HttpSession httpSession;
	
	@Autowired
	TJobsDao TJobsDao;
	
	@Autowired 
	JobApplication jobApplication;
	
	@Autowired
	TJobsDao tJob;
	
	@GetMapping("/allJobs")
	public List<Job> getAllJobs(){
		return tJob.list();
	}
	
	
	@RequestMapping("/postJob/")
	public Job postJob(@RequestBody Job job)
	{
		if(TJobsDao.save(job)){
			
			job.setErrorCode("200");
		job.setErrorMessage("successfully posted job");
	}
	
	else
	{
		
			job.setErrorCode("404");
		job.setErrorMessage("could not post job");
				
	}
	return job;
	

}
	
	

	
	//Accepting 
	
	
	@PostMapping("/jobApplication/accept/{jobId}/{userId}")
	public JobApplication acceptJobApplication(@PathParam("jobId") String jobId,@PathParam("userId") String userId){
		jobApplication=TJobsDao.get(userId, jobId);
		
			if(jobApplication==null){
				jobApplication=new JobApplication();
				jobApplication.setErrorCode("404");
				jobApplication.setErrorMessage(userId + "Doesnot applied for job" +jobId + "so you cannot accept");
						return jobApplication;
		}
		jobApplication.setStatus('A');
				return updateJobApplicationStatus(jobApplication);
	}
	
	
	




private JobApplication updateJobApplicationStatus(JobApplication jobApplication)

{
	if(TJobsDao.update(jobApplication))
	{
		jobApplication.setErrorCode("200");
		jobApplication.setErrorMessage("successfully updated the job status");
		
	}
	
	else
	{
		jobApplication.setErrorCode("404");
		jobApplication.setErrorMessage("could not able to update job status");
		
	}
	return jobApplication;
}



@GetMapping("job/jobApplication/{jobId}")
public JobApplication applyForJob(@RequestParam("jobId") String jobId){
	String loggedInUserId=(String) httpSession.getAttribute("loggedInUserId");
jobApplication.setJobID(jobId);
jobApplication.setStatus('N');
jobApplication.setUserID(loggedInUserId);

//check whether user is logged or not
if(loggedInUserId==null)
{
	jobApplication.setErrorCode("404");
	jobApplication.setErrorMessage("please login to apply job");
	return  jobApplication;
}


//create a new record in JobApplication TAble

JobApplication.setJobID(jobId);
jobApplication.setStatus('N');
JobApplication.setUserID(loggedInUserId);
jobApplication.setDateApplied(new Date(System.currentTimeMillis()));


//check whether the user is alrady applied for this particular job
if(isUserAlredyAppliedForJob(loggedInUserId,jobId))
{
	jobApplication.setErrorCode("404");
	jobApplication.setErrorMessage("you already applied gfor job"+jobId);
	return jobApplication;
}
if(TJobsDao.save(jobApplication)){
	jobApplication.setErrorCode("200");
	jobApplication.setErrorMessage("successfully aplied for job"+jobId);
	
}
else{
	jobApplication.setErrorCode("404");
	jobApplication.setErrorMessage("couldnot able to apply for job"+jobId);
	
}

}


/*@GetMapping("/job/jobApplication/{jobId}")
public jobApplication applyForJob
*/

private boolean isUserAlredyAppliedForJob(String userId, String jobId)
{
	if(TJobsDao.get(userId,jobId)==null){
		return false;
	}
	
	else{
		return true;
	}
}









}

