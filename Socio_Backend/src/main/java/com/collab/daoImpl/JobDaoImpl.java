package com.collab.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.collab.dao.JobDao;
import com.collab.domain.Job;
import com.collab.domain.JobApplication;

//import ch.qos.logback.classic.Logger;

public class JobDaoImpl implements JobDao {
	 final static Logger logger=Logger.getLogger("JobDaoImpl.class");
	// final static Logger logger=Logger.getLogger(FriendDaoImpl.class);
		@Autowired 
		SessionFactory sessionFactory;
		private Session getCurrentSession(){
			return sessionFactory.getCurrentSession();
		}
		
	
	
	private Long getMaxId(){
		logger.debug("starting of method getMaxId");
		Long maxId=100L;
		try{
			String hql="select max(id) from JobApplication";
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
			maxId=(Long) query.uniqueResult();
			
		}
		
catch(HibernateException e){
	logger.debug("it seems this is first record.setting initial id is 100");
	maxId=100L;
	e.printStackTrace();
}
		logger.debug("MaxId:"+maxId);
		return maxId+1;
}
	
	
	
	
	/*public List<Job> getAllOpenedJobs() {
		// TODO Auto-generated method stub
		logger.debug("Starting of method getAllOpenedJObs");
	String hql="from Job where status='"+"V'";
	Query query=sessionFactory.getCurrentSession().createQuery(hql);
	logger.debug("starting of method ");
	return (List<Job>) sessionFactory.getCurrentSession().createCriteria(Job.class)
			.add(Restrictions.eqProperty("status", "V")).list();	
		return query.list();
	}*/

	public Job getJobDetails(Long id) {
		// TODO Auto-generated method stub
		
			return (Job) sessionFactory.getCurrentSession().get(Job.class,id);
		}
		
	

	public boolean updateJob(Job job) {
		// TODO Auto-generated method stub
		try{
			sessionFactory.getCurrentSession().update(job);
			return true;}
		catch(HibernateException e){
			e.printStackTrace();
		return false;
	}
	}

	
	public boolean save(Job job) {
		// TODO Auto-generated method stub
		logger.debug("starting of save Job");
		try{
			sessionFactory.getCurrentSession().save(job);
			
		}
		catch(HibernateException e){
			e.printStackTrace();
		
		return false;
		}
		return true;
	}
	
	
	
	
	public boolean save(JobApplication jobApplied) {
		// TODO Auto-generated method stub
		logger.debug("Starting of the save job Application");
		try{
			jobApplied.setId(getMaxId());
			sessionFactory.getCurrentSession().save(jobApplied);
		}
		catch(HibernateException e){
			e.printStackTrace();
			
		
		return false;
		}
		return true;
		}


	
	
	
	public List<Job> getMyAppliedJobs(String userId) {
		// TODO Auto-generated method stub
		logger.debug("starting of method getMyAppliedJobs");
		String hql="from JobApplied where userId='"+ userId+"'";
	Query query=sessionFactory.getCurrentSession().createQuery(hql);
	
		return query.list();
	}

	
	
	public JobApplication getJobApplication(String userId, Long jobId) {
		// TODO Auto-generated method stub
		logger.debug("starting of the method getJobApplication");
		String hql="from JobApplication where userId='"+userId+"' and jobId '"+ jobId"'";
				logger.debug("hql"+hql);
		
		return  (JobApplication) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		
	}

	public JobApplication getJobApplication(Long id) {
		// TODO Auto-generated method stub
		
		return (JobApplication) sessionFactory.getCurrentSession().get(JobApplication.class, id);
		
	}

	public boolean updateJob(JobApplication jobApplication) {
		// TODO Auto-generated method stub
		try{
			sessionFactory.getCurrentSession().update(jobApplication);
			return true;}
		catch(HibernateException e){
			e.printStackTrace();
		return false;
	}

}



	public boolean saveJobApplication(JobApplication jobApplication) {
		// TODO Auto-generated method stub
		return false;
	}
}