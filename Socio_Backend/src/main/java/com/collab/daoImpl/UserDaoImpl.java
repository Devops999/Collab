package com.collab.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

//import javax.persistence.Query;

//import javax.transaction.Transaction;
//import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import org.hibernate.annotations.common.util.impl.Log_.logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collab.dao.UserDao;
import com.collab.domain.User;

@Repository
@Transactional

public class UserDaoImpl implements UserDao {
	@Autowired
	User user;
	@Autowired
	private SessionFactory sessionFactory;
	public static Logger logger= Logger.getLogger(UserDaoImpl.class);


	/*public boolean save(User user) {

		try {

			Session session = sessionFactory.openSession();
			Transaction tx = (Transaction) session.beginTransaction();
			// user.setRole("ROLE_USER");
			System.out.println(user.toString());
			session.save(user);
			tx.commit();
			session.flush();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}*/

	public boolean update(User user) {

		try

		{
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			// User user1 = (User) session.get(User.class, userId);
			System.out.println("getting user********");
			session.update(user);
			tx.commit();
			System.out.println("updating user***********");
			session.flush();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		Session session = (Session) sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<User> list = session.createQuery("from User").list();
		tx.commit();
		session.flush();
		session.close();
		return list;

	}

	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			User user = (User) session.get(User.class, userId);
			tx.commit();
			session.flush();
			session.close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	public boolean delete(int userId) {
		try {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			User user = (User) session.get(User.class, userId);

			session.delete(user);
			tx.commit();
			session.flush();
			session.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void registration(User user) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		session.save(user); 
        session.flush();
        session.close();
		
	}

	public User login(User user) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Users where username=? and password=? and enabled=?");
		query.setString(0, user.getFirstName());
		query.setString(1, user.getPassword());
		query.setBoolean(2, true);
		User validUser=(User) query.uniqueResult();
		session.close();
		return validUser;
		
	}
	

	public User authentication(String userId,String password){
		return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("id", userId))
				.add(Restrictions.eq("passowrd",password))
				.uniqueResult();
	}
	
	public void setOnline(String userId){
		logger.debug("staring of method setOnline");
		String hql="UPDATE User SET isOnline='y' where id='" + userId+ "'";
		logger.debug("ending of the method setOffLine");
	
	}
	
	
	public void setoffLine(String userId)
{
		logger.debug("staring of method setOfLine");
		String hql="UPDATE User SET isOnline='N' where id='" +  userId + "'";
				logger.debug("hql:"+hql);
				Query query=sessionFactory.getCurrentSession().createQuery(hql);
				query.executeUpdate();
				logger.debug("ending of method isOfline");
				}
}

	

