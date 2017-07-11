package com.collab.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

//import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.collab.dao.FriendDao;
import com.collab.domain.Friend;
import com.collab.restcontrollers.FriendRestController;
import com.collab.domain.BaseDomain;

public class FriendDaoImpl implements FriendDao{
	final static Logger logger=Logger.getLogger(FriendDaoImpl.class);
private SessionFactory sessionFactory;
public  FriendDaoImpl(SessionFactory sessionFactory){
	try{
		this .sessionFactory=sessionFactory;
		
	}
	catch(Exception e){
		logger.error("unable to connect to db");
	    e.printStackTrace();	
	}
}
	
	private Integer getMaxId(){
		logger.debug("staring of method getMaxId");
		String hql="select max(id) from friend";
		Query query=sessionFactory.openSession().createQuery(hql);
		Integer maxId;
		try{
			maxId=(Integer)query.uniqueResult();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 100;
		}
		logger.debug("Max id:"+maxId);
		return maxId;
	}
	
	

@Transactional
public boolean save(Friend friend)
{
	try{
		logger.debug("*******previous id"+getMaxId());
		friend.setId(getMaxId()+1);
		logger.debug("*****generate id"+getMaxId());
		sessionFactory.getCurrentSession().save(friend);
		return true;
	}
	catch(HibernateException e){
		e.printStackTrace();
		return false;
	}
}

@Transactional
public boolean update(Friend friend){
	try{
		logger.debug("userId : "+friend.getUserId()+"friendId:" +friend.getFriendId());
		
		
		sessionFactory.getCurrentSession().update(friend);
		logger.debug("successfully updated");
		return true;
		
	}
	catch(HibernateException e){
		e.printStackTrace();
		logger.debug("not able to update the status");
		return false;
		
		
	}
}


@Transactional
public void delete(String userId,String friendId){
	Friend friend=new Friend();
	friend.setFriendId(friendId);
	friend.setUserId(userId);
	sessionFactory.openSession().delete(friend);
}

public List<Friend> getMyFriends(String userId){
	String hql1="select freindId from Friend where userId='"+userId +"' and status='A'";
	String hql2="select userId from Friend where friendId='"+userId +"' and status='A'";
	logger.debug("getMyFriends hql1:"+hql1);
 logger.debug("getMyFriends hql1:"+hql2);
List<Friend> list1=sessionFactory.openSession().createQuery(hql1).list();
List<Friend> list2=sessionFactory.openSession().createQuery(hql2).list();
list1.addAll(list2);
return list1;
}

public List<Friend>getNewFriendRequests(String friendId){
	String hql="select userId from Friend where friendId=" +  "'"+ friendId +"' and status='"+ "N";
	logger.debug(hql);
	return sessionFactory.openSession().createQuery(hql).list();
	
}




public List<Friend> getRequestsSendByMe(String userId){
	String hql="select friendId fromm Friend where userId="  + "'"+userId +"' and status='"+"N'";
			logger.debug(hql);
			return sessionFactory.openSession().createQuery(hql).list();
	
}



//@Override
public Friend get(String userId, String friendId) {
	
	
	String hql="from Freind where userId=" + "'" + userId + "' and FriendId='" + friendId;
		Query query=	sessionFactory.openSession().createQuery(hql);
	
	// TODO Auto-generated method stub
	return (Friend)query.uniqueResult();
}

public Friend get(String friendId) {
	// TODO Auto-generated method stub
	return null;
}

//@Override
public void setOnline(String friendId) {
	// TODO Auto-generated method stub
	logger.debug("starting of the method isOnline");
	//update friend set is online="y"
	String hql="UPDATE Friend SET isOnline='Y' where friendId=?";
	
}


public void setOffline(String friendId) {
	// TODO Auto-generated method stub
	logger.debug("starting of the method setOffline");
	String hql="UPDATE Friend SET isOffline='N' where friendId='"+friendId+"'";
	logger.debug("hql:"+hql);
	Query query=sessionFactory.openSession().createQuery(hql);
	query.executeUpdate();
	logger.debug("Ending of setOfflineMethod");
}









}


