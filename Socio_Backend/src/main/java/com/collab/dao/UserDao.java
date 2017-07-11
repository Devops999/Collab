package com.collab.dao;

import java.util.List;

import com.collab.domain.User;

public interface UserDao {
	//public boolean save(User user);

	public List<User> getAllUser();

	public User getUserById(String userId);

	public boolean delete(int userId);
	public boolean update(User user);
	
	
	void registration(User user);
	//List<User> getAllUsers();
	User login(User user);
	
	public User authentication(String userId,String password);



		public void setOnline(String userId);
		public void setoffLine(String userId);

	
	

}
