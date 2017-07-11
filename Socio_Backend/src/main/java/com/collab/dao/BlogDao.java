package com.collab.dao;

import java.util.List;

import com.collab.domain.Blog;

public interface BlogDao {
	public boolean save(Blog blog);

	public List<Blog> getAllBlog();

	public Blog getBlogById(int BlogID);

	public boolean delete(int blogId);
	public boolean update(Blog blog);
	

	

	
	

}
