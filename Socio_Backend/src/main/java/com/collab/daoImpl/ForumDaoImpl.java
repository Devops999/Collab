package com.collab.daoImpl;

import java.util.List;

//import javax.transaction.Transaction;
//import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collab.dao.ForumDao;
import com.collab.domain.Forum;

@Repository
@Transactional

public class ForumDaoImpl implements ForumDao {
	@Autowired
	Forum forum;
	@Autowired
	private SessionFactory sessionFactory;

	public boolean save(Forum forum) {

		try {

			Session session = sessionFactory.openSession();
			Transaction tx = (Transaction) session.beginTransaction();
			// forum.setRole("ROLE_USER");
			System.out.println(forum.toString());
			session.save(forum);
			tx.commit();
			session.flush();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean update(Forum forum) {

		try

		{
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			// Forum forum1 = (Forum) session.get(Forum.class, forumId);
			System.out.println("getting forum********");
			session.update(forum);
			tx.commit();
			System.out.println("updating forum***********");
			session.flush();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<Forum> getAllForum() {
		// TODO Auto-generated method stub
		Session session = (Session) sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Forum> list = session.createQuery("from Forum").list();
		tx.commit();
		session.flush();
		session.close();
		return list;

	}

	public Forum getForumById(int ForumID) {
		// TODO Auto-generated method stub
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Forum forum = (Forum) session.get(Forum.class, ForumID);
			tx.commit();
			session.flush();
			session.close();
			return forum;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return forum;
	}

	public boolean delete(int forumId) {
		try {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			Forum forum = (Forum) session.get(Forum.class, forumId);

			session.delete(forum);
			tx.commit();
			session.flush();
			session.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
