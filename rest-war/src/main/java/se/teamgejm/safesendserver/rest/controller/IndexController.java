package se.teamgejm.safesendserver.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Emil Stjerneman
 */

@Controller
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {

		return new ModelAndView("redirect:https://bitbucket.org/teamgejm/server/wiki/REST%20API%20Documentation");
	}

	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public ResponseEntity error500() {

		return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public ResponseEntity error404() {

		return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);

	}

}
