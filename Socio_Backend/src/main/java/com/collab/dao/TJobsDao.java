package com.collab.dao;

import java.util.List;

import com.collab.domain.Job;
import com.collab.domain.JobApplication;

public interface TJobsDao {
	
			public boolean save(Job job);
			public boolean update(Job job);
			public List<Job> list();
			
			
			//Student/Alumni---get all vacant jobs
			//Admin also required to get all jobs by status
			public List<Job> list(Character status);
			
			//apply for job--> create new record in jobApplication table
			public boolean save(JobApplication jobApplication);
			
			
			//Admin can reject/select/call for interview
			
			public boolean update(JobApplication jobApplication);
			
			
			//get all the jobs applied by me 
			public List<JobApplication> list (String userID);
			
			
			public JobApplication get(String userId, String jobId);
			
			

		

	}


