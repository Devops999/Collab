package com.collab.dao;

import java.util.List;

import com.collab.domain.Forum;

public interface ForumDao {

	public boolean save(Forum forum);

	public List<Forum> getAllForum();

	public Forum getForumById(int ForumID);

	public boolean delete(int forumId);

	public boolean update(Forum forum);

}
