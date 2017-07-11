package com.collab.daoImpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
//import org.hibernate.criterion.Restrictions;
//import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.collab.dao.TJobsDao;
import com.collab.domain.Job;
import com.collab.domain.JobApplication;
@Repository("tJobDao")
@Transactional
public class TJobsDaoImpl implements TJobsDao {
	

	
		@Autowired 
		SessionFactory sessionFactory;
		private Session getCurrentSession(){
			return sessionFactory.getCurrentSession();
		}
		
		
		
		
		
		private Long getMaxJobApplicationId()
		{
			return  (Long) getCurrentSession().createQuery("select max(id) from JobApplication").uniqueResult(); 

		}
		
		
		
		private Long getMaxJobId(){
			
			return (Long) getCurrentSession().createQuery("select max(id) from job").uniqueResult();
		}
		
		public boolean save(Job job) {
			// TODO Auto-generated method stub
			try{
				JobApplication.setId(getMaxJobApplicationId()+1);
				
				getCurrentSession().save(job);
				
			}
			catch(Exception e){
				e.printStackTrace();
				
			
			return false;
			}
			return true;
		}
		
		
		
	
		public boolean update(Job job) {
			// TODO Auto-generated method stub
			try{
				getCurrentSession().update(job);
			}
			catch(Exception e){
				e.printStackTrace();
			
			return false;}
			return true;
		}
		
		
		
	
		public List<Job> list() {
			// TODO Auto-generated method stub
			return getCurrentSession().createQuery("from Job").list();
		}
		
		
		
		public List<Job> list(Character status) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
	
		public boolean save(JobApplication job) {
			// TODO Auto-generated method stub
			try{
				
				job.setId(getMaxJobId()+1);
				job.setDateApplied(new Date(System.currentTimeMillis()));
				job.setStatus('V');
				getCurrentSession().save(job);
			}
			catch(Exception e){
				e.printStackTrace();
			
			return false;
			}return true;
		}
		
		
		
		public boolean update(JobApplication job) {
			// TODO Auto-generated method stub
			
						try{
							getCurrentSession().update(job);
						}
						catch(Exception e){
							e.printStackTrace();
						
						return false;
						}return true;
					}
		
	
		public List<JobApplication> list(String userID) {
			// TODO Auto-generated method stub
			return getCurrentSession().createCriteria(JobApplication.class)
					.add(Restrictions.eq("userID",userID)).list();
		}

		public JobApplication get(String userId, String jobId) {
			// TODO Auto-generated method stub
			
			return (JobApplication) getCurrentSession().createCriteria(JobApplication.class);
			.add(Restrictions.eq("userId", userId))
			.add(Restrictions.eq("jobId", jobId))
		.uniqueResult();
		}

		
	}


