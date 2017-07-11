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

import com.collab.dao.BlogDao;
import com.collab.domain.Blog;


@Repository
@Transactional

public class BlogDaoImpl implements BlogDao {
	@Autowired
	Blog blog;
	@Autowired
	private SessionFactory sessionFactory;

	public boolean save(Blog blog) {

		try {

			Session session = sessionFactory.openSession();
			Transaction tx = (Transaction) session.beginTransaction();
			// blog.setRole("ROLE_USER");
			System.out.println(blog.toString());
			session.save(blog);
			tx.commit();
			session.flush();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean update(Blog blog) {

		try

		{
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			// Blog blog1 = (Blog) session.get(Blog.class, blogId);
			System.out.println("getting blog********");
			session.update(blog);
			tx.commit();
			System.out.println("updating blog***********");
			session.flush();
			session.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<Blog> getAllBlog() {
		// TODO Auto-generated method stub
		Session session = (Session) sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Blog> list = session.createQuery("from Blog").list();
		tx.commit();
		session.flush();
		session.close();
		return list;

	}

	public Blog getBlogById(int BlogID) {
		// TODO Auto-generated method stub
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Blog blog = (Blog) session.get(Blog.class, BlogID);
			tx.commit();
			session.flush();
			session.close();
			return blog;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return blog;
	}

	public boolean delete(int blogId) {
		try {

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			Blog blog = (Blog) session.get(Blog.class, blogId);

			session.delete(blog);
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

	