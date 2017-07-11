package com.collab.restcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NormalController {

	@RequestMapping(value = "/")
	public String giveHomepage() {

		return "Home";

	}

}
