package com.collab.restcontrollers;

import org.apache.log4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class indexController {
	@Controller
	public class IndexController{

		
		private final Logger  logger= Logger.getLogger(IndexController.class);
	
	@RequestMapping("/")
	public String getIndexPage(){
		logger.debug("starting of method getIndexPage");
		return "server is running ";
	}
	}

}
