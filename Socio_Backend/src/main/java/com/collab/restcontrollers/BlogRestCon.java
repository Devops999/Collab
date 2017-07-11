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

import com.collab.dao.BlogDao;
import com.collab.domain.Blog;

@RestController
public class BlogRestCon {
	@Autowired
	BlogDao blogDao;

	@RequestMapping(value = "/addblog", method = RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody Blog blog) {

		System.out.println(blog.toString());
		blogDao.save(blog);

		return new ResponseEntity<String>("successfuly inserted", HttpStatus.OK);

	}

	@RequestMapping(value = "/getAllBlog", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> getAllBlog() {
		System.out.println("Hit***");
		List<Blog> listblogs = blogDao.getAllBlog();
		return new ResponseEntity<List<Blog>>(listblogs, HttpStatus.OK);

	}

	@RequestMapping(value = "/deleteBlog/{BlogID}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBlog(@PathVariable("BlogID") int blogId) {

		if (blogDao.delete(blogId)) {

			return new ResponseEntity<String>("successfuly deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Not successfuly deleted", HttpStatus.NOT_FOUND);

		}

	}

	/*@RequestMapping(value = "/updateBlog/{BlogId}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateBlog(@PathVariable("BlogId") int blogId, @RequestBody Blog blog) {

		{
			System.out.println("Entering inside update contrller");
			Blog curr_blog = blogDao.getBlogById(blogId);

			// curr_blog.setBlogId(100);
			if (blogDao.update(blog))

			{

				return new ResponseEntity<String>("Successsfully updated", HttpStatus.OK);

			}

			else {
				return new ResponseEntity<String>("Not updated", HttpStatus.NOT_FOUND);
			}

		}
	}*/
	
	
	//ANother way of updating
	@RequestMapping(value="/getBlogById/{BlogId}",method=RequestMethod.GET)
	public ResponseEntity<Blog> getPersonById(@PathVariable int id){
		Blog blog=blogDao.getBlogById(id
				);
		if(blog==null)
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	//step 2 fr updating
	@RequestMapping(value="/updateBlog",method=RequestMethod.PUT)
	public ResponseEntity<Void> updatePerson(@RequestBody Blog blog){
		int id=blog.getBlogId();
		Blog blogAvailable=blogDao.getBlogById(id);
		if(blogAvailable==null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		else
			blogDao.update(blog);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}	
	
	
}
