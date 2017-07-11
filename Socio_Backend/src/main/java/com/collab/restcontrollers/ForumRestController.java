package com.collab.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collab.dao.ForumDao;
import com.collab.domain.Forum;

@RestController
public class ForumRestController {
	@Autowired
	ForumDao forumDao;

	@RequestMapping(value = "/addforum", method = RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody Forum forum) {

		System.out.println(forum.toString());
		forumDao.save(forum);

		return new ResponseEntity<String>("successfuly inserted", HttpStatus.OK);

	}

	@RequestMapping(value = "/getAllForum", method = RequestMethod.GET)
	public ResponseEntity<List<Forum>> getAllForum() {
		System.out.println("Hit***");
		List<Forum> listforums = forumDao.getAllForum();
		return new ResponseEntity<List<Forum>>(listforums, HttpStatus.OK);

	}

	@RequestMapping(value = "/deleteForum/{ForumID}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteForum(@PathVariable("ForumID") int forumId) {

		if (forumDao.delete(forumId)) {

			return new ResponseEntity<String>("successfuly deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not successfuly deleted", HttpStatus.NOT_FOUND);

		}

	}

	@RequestMapping(value = "/updateForum/{ForumId}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateForum(@PathVariable("ForumId") int forumId, @RequestBody Forum forum) {

		{
			Forum curr_forum = forumDao.getForumById(forumId);

			//curr_forum.setForumID(100);
			forumDao.update(forum);

			{

				return new ResponseEntity<String>("Successsfully updated", HttpStatus.OK);

			}

		}
	}
}
