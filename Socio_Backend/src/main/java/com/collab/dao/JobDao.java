package com.collab.dao;


	
	

	import java.util.List;

	import com.collab.domain.Job;
	import com.collab.domain.JobApplication;

	
	public interface JobDao {

		
		public  List<Job> getAllOpenedJobs();
		
		public Job getJobDetails(Long id);
		
		public boolean updateJob(Job job);
		public boolean updateJob(JobApplication jobApplication);
		
		
		public boolean save(JobApplication jobApplied);
		public boolean save(Job job);
		public boolean saveJobApplication(JobApplication jobApplication);
		
		
		public List<Job> getMyAppliedJobs(String userId);
		public JobApplication getJobApplication(String userId,Long jobId);
		
		public JobApplication getJobApplication(Long id);
		
		
		
		
	}



